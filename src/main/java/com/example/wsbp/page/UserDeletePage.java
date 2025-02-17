package com.example.wsbp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wsbp.service.IUserService;

@MountPath("UserDelete")
public class UserDeletePage extends WebPage {

    @SpringBean
    private IUserService userService;


    public UserDeletePage() {
        var userNameModel = Model.of("");

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        var userInfoForm = new Form<>("userInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();

                var msg = "送信データ："
                        + userName;
                System.out.println(msg);
                userService.removeUser(userName);

                setResponsePage(new UserDeleteCompPage(userNameModel));
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName1", userNameModel);
        userInfoForm.add(userNameField);
    }

}