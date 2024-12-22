package com.example.wsbp.page.signed;

import com.example.wsbp.data.Lecture_detail;
import com.example.wsbp.data.Lecture;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.page.UserMakerCompPage;
import com.example.wsbp.service.IUserService;
import com.example.wsbp.service.UserService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

@WicketHomePage
@MountPath("LecturePage")
public class LecturePage extends WebPage {

    @SpringBean
    private IUserService userService;

    public LecturePage(PageParameters parameters) {
        super(parameters);


        // パラメータから値を取得
        String subjectName = parameters.get("subject_name").toString("不明な科目");
        String subjectId = parameters.get("subject_id").toString("不明なID");
//        int subjectId = Integer.parseInt(parameters.get("subject_id").toString());
        String name = parameters.get("Username").toString("不明な名前");


        var SubjectLabel = new Label("subject_name", subjectName);
        add(SubjectLabel);

        var toUserMakerLinks = new BookmarkablePageLink<>("toSubject", SignedPage.class);
        add(toUserMakerLinks);


//        //講義のデータベースの情報を全て表示する
//
//        var lecturesModel = Model.ofList(userService.findLectures());
//
//        // List型のモデルを表示する ListView
//        var usersLV = new ListView<>("lectures", lecturesModel) {
//            @Override
//            protected void populateItem(ListItem<Lecture> listItem) {
//                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
//                var itemModel = listItem.getModel();
//                var authUser = itemModel.getObject(); // 元々のListの n 番目の要素
//
//                int subjectId = Integer.parseInt(parameters.get("subject_id").toString());
//                int subject_idModel = Integer.parseInt(authUser.getlecture_serial_Num());
//
//                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
//                // add する先が listItem になることに注意。
//                var serial_numModel = Model.of(authUser.getlecture_serial_Num());
//                var serial_numLabel = new Label("lecture_serial_num", serial_numModel);
//                listItem.add(serial_numLabel);
//
//                var detailModel = Model.of(authUser.getDetail());
//                var detailLabel = new Label("detail", detailModel);
//                listItem.add(detailLabel);
//
//                if (subjectId == subject_idModel) {
//
//                }
//            }
//        };

//        講義の説明を科目別に表示するとしないでわける。以下のコードは送られてきた科目IDを使ってソートするようにするもの

        try{
            var lecturesModel = Model.ofList(userService.finddetail(subjectId));
            // List型のモデルを表示する ListView
            var usersLV = new ListView<>("lectures", lecturesModel) {
                @Override
                protected void populateItem(ListItem<Lecture_detail> listItem) {
                    // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                    var itemModel = listItem.getModel();
                    var authUser = itemModel.getObject(); // 元々のListの n 番目の要素

                    // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                    // add する先が listItem になることに注意。
                    var serial_numModel = Model.of(authUser.getlecture_serial_Num());
                    var serial_numLabel = new Label("lecture_serial_num", serial_numModel);
                    listItem.add(serial_numLabel);

                    var detailModel = Model.of(authUser.getDetail());
                    var detailLabel = new Label("detail", detailModel);
                    listItem.add(detailLabel);

                    //出席状況を表示する　- or 〇 lecture_attendanceにユーザ情報があれば〇なければ-
                    String state;
                    if (userService.exsitpass(authUser.getlecture_id(),name)) {
                        state = "〇";
                    }else{
                        state = "-";
                    }
                    var attendanceModel = Model.of(state);
                    var attendanceLabel = new Label("attendance", attendanceModel);
                    listItem.add(attendanceLabel);

//                    //lecture_idを表示
//                    var testModel = Model.of(authUser.getlecture_id());
//                    var testLabel = new Label("test", testModel);
//                    listItem.add(testLabel);

                    var lecturespass = userService.findpass(authUser.getlecture_id()); //設定されているパスワードをリストで取得　ない場合は[]になる
                    String non ;
                    boolean showForm = (!lecturespass.isEmpty() &&  !(userService.exsitpass(authUser.getlecture_id(),name)));
                    if(showForm){
                        non = "パスワードあります";
                    }else{
                        non = "パスワードありません";
                    }
                    if (!lecturespass.isEmpty()) { //パスワードが設定されているかの調査
                        if(userService.exsitpass(authUser.getlecture_id(),name)){ //パスワードが登録されているかのチェック
                            var aLabel = new Label("pass", "パスワード入力済み");
                            listItem.add(aLabel);
                        }else{
                            var aLabel = new Label("pass", lecturespass.getFirst().getlecture_Password());
                            listItem.add(aLabel);
                        }
                    } else {
                        var aLabel = new Label("pass", non);
                        listItem.add(aLabel);
                    }

                    try{
                        //フォームの作成
                        Form<?> passwordForm = new Form<>("passwordform");
                        //boolean shouldHideForm = true; // 非表示にする条件をここで指定
                        passwordForm.setVisible(showForm);

                        passwordForm.setOutputMarkupId(true);// Ajax更新を可能にする

                        // パスワード入力フィールド
                        Model<String> passwordModel = Model.of("");
                        PasswordTextField passwordTextField = new PasswordTextField("password", passwordModel);
                        passwordTextField.setRequired(true);
                        passwordTextField.add(StringValidator.exactLength(4));
                        passwordForm.add(passwordTextField);

                        // ラベル（Ajaxで更新可能）
                        Label pass = new Label("inputpass", Model.of("パスワード入力できます"));
                        pass.setOutputMarkupId(true);// Ajax更新を可能にする
                        passwordForm.add(pass);

                        // Ajaxボタン
                        AjaxButton submitButton = new AjaxButton("submit", passwordForm) {
                            @Override
                            protected void onSubmit(AjaxRequestTarget target) {
                                String password = passwordTextField.getModelObject();
                                if (password.equals(lecturespass.getFirst().getlecture_Password())) {
                                    pass.setDefaultModelObject("パスワード一致");
                                    passwordTextField.setVisible(false);// フォームを非表示
                                    this.setVisible(false); // 送信ボタンを非表示に
                                    userService.insertpass(authUser.getlecture_id(),name);

                                } else {
                                    pass.setDefaultModelObject("パスワードが違います");
                                }
                                target.add(pass,passwordForm,this);// ラベルとフォームをAjax更新
                            }
                            @Override
                            protected void onError(AjaxRequestTarget target){
                                super.onError(target);
                            }
                        };
                        submitButton.setOutputMarkupId(true);// Ajax更新可能に
                        passwordForm.add(submitButton);
                        listItem.add(passwordForm);
                    }catch(Exception e){
                        System.out.println("パスワード入力に失敗しました");
                        e.printStackTrace();
                    }

                }
            };
            add(usersLV);
        }catch(Exception e){
            System.out.println("引数が違います");
            e.printStackTrace();
        }
    }

}