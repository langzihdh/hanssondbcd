package com.hansson.dybalcalc.view.main;

import com.hansson.dybalcalc.view.menu.HanssonMenu;
import com.hansson.dybalcalc.view.menu.HanssonNavigator;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

public class MainView extends HorizontalLayout{
    public MainView() {
        setSizeFull();
        addStyleName("mainview");

        addComponent(new HanssonMenu());

        ComponentContainer content = new CssLayout();
        content.addStyleName("view-content");
        content.setSizeFull();
        addComponent(content);
        setExpandRatio(content, 1.0f);

        new HanssonNavigator(content);
    }
}
