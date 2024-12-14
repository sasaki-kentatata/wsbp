package com.example.wsbp.page.signed;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@WicketHomePage
@MountPath("LecturePage")
public class LecturePage extends WebPage {

    public LecturePage() {
        var toUserMakerLinks = new BookmarkablePageLink<>("toSubject", SubjectPage.class);
        add(toUserMakerLinks);
    }

}