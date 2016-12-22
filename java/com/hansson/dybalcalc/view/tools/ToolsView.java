package com.hansson.dybalcalc.view.tools;

import com.hansson.dybalcalc.component.BuildBaner;
import com.hansson.dybalcalc.component.ContentWrapper;
import com.hansson.dybalcalc.event.HanssonEvent.CloseOpenWindowsEvent;
import com.hansson.dybalcalc.event.HanssonEventBus;
import com.hansson.dybalcalc.view.tools.ToolsEdit.DashboardEditListener;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class ToolsView extends Panel implements View,
        DashboardEditListener {

    public static final String EDIT_ID = "dashboard-edit";
    public static final String TITLE_ID = "dashboard-title";

    private Label titleLabel;
    private CssLayout dashboardPanels;
    private final VerticalLayout root;
	private ContentWrapper conWrap;
	private float h;
	private float w;
	private Unit hu;
	private Unit wu;

    public ToolsView() {

        addStyleName(ValoTheme.PANEL_BORDERLESS);
        setSizeFull();
        HanssonEventBus.register(this);
        root = new VerticalLayout();

        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);
        
        root.addComponent(new BuildBaner());
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
        dashboardPanels.addComponent(buildTrialWeightPanel());
        dashboardPanels.addComponent(buildTVectorCalc());
        dashboardPanels.addComponent(buildVectorDec());
        dashboardPanels.addComponent(buildAllowUnbalance());

        return dashboardPanels;
    }

    private Component buildTrialWeightPanel() {
        Component trialWeightPanel = conWrap.wrapper(new TrialWeightPanel());
        h=trialWeightPanel.getHeight();
        w=trialWeightPanel.getWidth();
        hu=trialWeightPanel.getHeightUnits();
        wu=trialWeightPanel.getWidthUnits();
        trialWeightPanel.addStyleName("top10-revenue");
        return trialWeightPanel;
    }

    private Component buildAllowUnbalance() {
        Component allowUnbalance = conWrap.wrapper(new AllowUnbalance());
        allowUnbalance.addStyleName("top10-revenue");
        return allowUnbalance;
    }

    private Component buildTVectorCalc() {
        Component vectorCalc = conWrap.wrapper(new VectorCalc());
    
        vectorCalc.addStyleName("top10-revenue");   
        vectorCalc.setHeight(h, hu);
        vectorCalc.setWidth(w, wu);
        return vectorCalc;
    }

    private Component buildVectorDec() {
        Component vectorDec = conWrap.wrapper(new VectorDec());
        vectorDec.addStyleName("top10-revenue");
        return vectorDec;
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        //notificationsButton.updateNotificationsCount(null);
    }

    @Override
    public void dashboardNameEdited(final String name) {
        titleLabel.setValue(name);
    }
}
