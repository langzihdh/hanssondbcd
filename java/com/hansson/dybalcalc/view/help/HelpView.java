package com.hansson.dybalcalc.view.help;

import com.hansson.dybalcalc.component.BuildBaner;
import com.hansson.dybalcalc.component.ContentWrapper;
import com.hansson.dybalcalc.event.HanssonEvent.CloseOpenWindowsEvent;
import com.hansson.dybalcalc.event.HanssonEventBus;
import com.hansson.dybalcalc.view.tools.ToolsEdit.DashboardEditListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class HelpView extends Panel implements View,
        DashboardEditListener {

    public static final String EDIT_ID = "dashboard-edit";
    public static final String TITLE_ID = "dashboard-title";

    private Label titleLabel;
    private CssLayout dashboardPanels;
    private final VerticalLayout root;
	private ContentWrapper conWrap;
	private CssLayout singlePanel;

    public HelpView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        HanssonEventBus.register(this);
        root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);
        

        
        root.addComponent(new BuildBaner());
        
        singlePanel = new CssLayout();
        singlePanel.addStyleName("dashboard-panels");
        ContentWrapper conWrap= new ContentWrapper(root, singlePanel);
 
        
  

        // All the open sub-windows should be closed whenever the root layout
        // gets clicked.
        root.addLayoutClickListener(new LayoutClickListener() {
            @Override
            public void layoutClick(final LayoutClickEvent event) {
                HanssonEventBus.post(new CloseOpenWindowsEvent());
            }
        });
    }


    @Override
    public void enter(final ViewChangeEvent event) {
        
    }

    @Override
    public void dashboardNameEdited(final String name) {
        titleLabel.setValue(name);
    }

}
