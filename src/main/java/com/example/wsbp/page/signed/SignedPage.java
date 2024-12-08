package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {
    public SignedPage() {
        var name = MySession.get().getUserName();
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);
    }
}