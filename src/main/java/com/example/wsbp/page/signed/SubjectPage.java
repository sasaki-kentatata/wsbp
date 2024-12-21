package com.example.wsbp.page.signed;

import com.example.wsbp.data.Subject;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.service.IUserService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.request.mapper.parameter.PageParameters;

@WicketHomePage
@MountPath("SubjectPage")
public class SubjectPage extends WebPage {


    // Service を IoC/DI する
    @SpringBean
    private IUserService userService;

    public SubjectPage(PageParameters parameters) {
        super(parameters);

        String Name = parameters.get("username").toString("不明な名前");

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        // なお、DBや外部のWEB-APIなどのデータを取得する場合、通常はLoadableDetachableModelを利用する
        // 参考：https://ci.apache.org/projects/wicket/guide/9.x/single.html#_detachable_models
        var subjectModel = Model.ofList(userService.findSubjects());

        // List型のモデルを表示する ListView
        var usersLV = new ListView<>("subjects", subjectModel) {
            @Override
            protected void populateItem(ListItem<Subject> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var subject = itemModel.getObject(); // 元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。

                PageParameters parameters = new PageParameters();
                parameters.add("subject_name", subject.getSubject_Name()); // 科目名を設定
                parameters.add("subject_id", subject.getID());
                parameters.add("Username",Name);

//                var idModel = Model.of(subject.getID());
//                var idLabel = new Label("subjectID", idModel);
////                listItem.add(idLabel);

                var toLectureLink = new BookmarkablePageLink<>("toLecture", LecturePage.class,parameters);
                var subjectModel = Model.of(subject.getSubject_Name());
                var sujectLabel = new Label("subjectName", subjectModel);
                toLectureLink.add(sujectLabel);

//                listItem.add(sujectLabel);
                listItem.add(toLectureLink);

            }
        };
        add(usersLV);

        var toUserMakerLink = new BookmarkablePageLink<>("signedpage", SignedPage.class);
        add(toUserMakerLink);


    }

}
