package com.hansson.dybalcalc.view.harmonic;

import com.hansson.dybalcalc.component.BuildBaner;
import com.hansson.dybalcalc.component.ContentWrapper;
import com.hansson.dybalcalc.event.HanssonEvent.CloseOpenWindowsEvent;
import com.hansson.dybalcalc.event.HanssonEventBus;
import com.hansson.dybalcalc.view.doubleplane.DoubleHatmonic;
import com.hansson.dybalcalc.view.singleplane.SingleCalc;
import com.hansson.dybalcalc.view.tools.ToolsEdit.DashboardEditListener;
import com.hansson.dybalcalc.view.tools.VectorCalc;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class HarmonicView extends Panel implements View,
        DashboardEditListener {

    public static final String EDIT_ID = "dashboard-edit";
    public static final String TITLE_ID = "dashboard-title";

    private Label titleLabel;
    private CssLayout dashboardPanels;
    private final VerticalLayout root;
	private ContentWrapper conWrap;
	private CssLayout doublePanel;

    public HarmonicView() {
        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        HanssonEventBus.register(this);
        root = new VerticalLayout();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);
        
        root.addComponent(new BuildBaner());
        
        doublePanel = new CssLayout();
        doublePanel.addStyleName("dashboard-panels");
        ContentWrapper conWrap= new ContentWrapper(root, doublePanel);
        Component hatmonicCalc = conWrap.wrapper(new DoubleHatmonic());
        //hatmonicCalc.addStyleName("top10-revenue");
        
        doublePanel.addComponent(hatmonicCalc);
        root.addComponent(doublePanel);
        
        Component content = buildContent();
        root.addComponent(content);
        root.setExpandRatio(content, 1);

        // All the open sub-windows should be closed whenever the root layout
        // gets clicked.
        root.addLayoutClickListener(new LayoutClickListener() {
            @Override
            public void layoutClick(final LayoutClickEvent event) {
                HanssonEventBus.post(new CloseOpenWindowsEvent());
            }
        });
    }

    private Component buildContent() {
        dashboardPanels = new CssLayout();
        dashboardPanels.addStyleName("dashboard-panels");
        Responsive.makeResponsive(dashboardPanels);
        conWrap= new ContentWrapper(root, dashboardPanels);
        //dashboardPanels.addComponent(buildSingleCalc());
        //dashboardPanels.addComponent(buildVectorCalc());

        return dashboardPanels;
    }

    private Component buildSingleCalc() {
        Component singleCalc = conWrap.wrapper(new SingleCalc());
        singleCalc.addStyleName("top10-revenue");
        return singleCalc;
    }

    private Component buildVectorCalc() {
        Component vectorCalc = conWrap.wrapper(new VectorCalc());
        vectorCalc.addStyleName("top10-revenue");
        return vectorCalc;
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        
    }

    @Override
    public void dashboardNameEdited(final String name) {
        titleLabel.setValue(name);
    }

}
