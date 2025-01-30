package com.example.wsbp.page.signed;

import com.example.wsbp.data.Chat;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.page.UserMakerCompPage;
import com.example.wsbp.service.IUserService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

@WicketHomePage
@MountPath("ChatPage")
public class ChatPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public ChatPage() {
        var userNameModel = Model.of("");
        var userMsgModel = Model.of("");

        try{
            var chatModel = Model.ofList(userService.findmsg());
            // List型のモデルを表示する ListView
            var chatsLV = new ListView<>("chat", chatModel) {
                @Override
                protected void populateItem(ListItem<Chat> listItem) {
                    // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                    var itemModel = listItem.getModel();
                    var authUser = itemModel.getObject(); // 元々のListの n 番目の要素

                    // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                    // add する先が listItem になることに注意。
                    var nameModel = Model.of(authUser.getUserName());
                    var nameLabel = new Label("post_name", nameModel);
                    listItem.add(nameLabel);

                    var messageModel = Model.of(authUser.getUserMessage());
                    var messageLabel = new Label("post_content", messageModel);
                    listItem.add(messageLabel);


                }
            };
            add(chatsLV);
        }catch(Exception e){
            System.out.println("引数が違います");
            e.printStackTrace();
        }

        var userInfoForm = new Form<>("post") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var userMsg = userMsgModel.getObject();
                var msg = "送信データ："
                        + userName
                        + ","
                        + userMsg;
                System.out.println(msg);
                userService.registpost(userName, userMsg);
                setResponsePage(new ChatPage());
            }
        };


        // フォームコンポーネントのバインディング
        userInfoForm.add(new TextField<>("userName", userNameModel));  // userName のフィールドをモデルにバインド
        userInfoForm.add(new TextField<>("userMsg", userMsgModel));
        add(userInfoForm);

        var toHomeLink = new BookmarkablePageLink<>("toSingedPage", SignedPage.class);
        add(toHomeLink);
    }

}