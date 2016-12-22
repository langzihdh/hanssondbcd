package com.hansson.dybalcalc.component;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class BuildBaner extends CustomComponent{

    private Label hansson;
    public static final String TITLE_ID = "dashboard-title";
    
	public BuildBaner() {
    	VerticalLayout header = new VerticalLayout();
        //header.addStyleName("viewheader");
        header.setWidth("100%");
        //header.addStyleName("valo-menu-title");

        hansson = new Label("<strong>Hansson 旋转机械动平衡计算系统</strong>",
        		ContentMode.HTML);
        hansson.setId(TITLE_ID);
        hansson.setSizeUndefined();
        hansson.addStyleName(ValoTheme.LABEL_H1);
        hansson.addStyleName(ValoTheme.LABEL_COLORED);
        hansson.addStyleName(ValoTheme.LABEL_BOLD);
        hansson.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        header.addComponent(hansson);
        header.setComponentAlignment(hansson, Alignment.MIDDLE_CENTER);
        setCompositionRoot(header);   
    }
}
