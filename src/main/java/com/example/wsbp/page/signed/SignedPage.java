package com.example.wsbp.page.signed;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("Signed")
public class SignedPage extends WebPage {
    public SignedPage() {
        var name = Model.of("test");
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);
    }
}