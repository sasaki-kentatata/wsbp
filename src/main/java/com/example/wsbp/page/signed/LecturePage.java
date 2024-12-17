package com.example.wsbp.page.signed;

import com.example.wsbp.data.Lecture_detail;
import com.example.wsbp.data.Lecture;
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

        var SubjectLabel = new Label("subject_name", subjectName);
        add(SubjectLabel);

        var toUserMakerLinks = new BookmarkablePageLink<>("toSubject", SubjectPage.class);
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

//        [目標]講義の説明を科目別に表示するとしないでわける。以下のコードは送られてきた科目IDを使ってソートするようにするもの

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

                }
            };
            add(usersLV);
        }catch(Exception e){
            System.out.println("引数が違います");
            e.printStackTrace();
        }
    }

}