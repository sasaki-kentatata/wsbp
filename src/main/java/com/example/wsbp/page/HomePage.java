package com.example.wsbp.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import com.example.wsbp.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    @SpringBean
    private ISampleService service;

    public HomePage() {
        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);
        var numberModel = Model.of("b2231050");
        var numberLabel = new Label("number", numberModel);
        add(numberLabel);
        var nameModel = Model.of("ケンタ");
        var nameLabel = new Label("name", nameModel);
        add(nameLabel);
        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        add(timeLabel);
        var randomModel = Model.of(service.makeRandInt());
        var randomLabel = new Label("random", randomModel);
        add(randomLabel);

        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);
    }

}