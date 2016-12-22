package com.hansson.dybalcalc.view.menu;


import com.hansson.dybalcalc.view.doubleplane.DoubleView;
import com.hansson.dybalcalc.view.harmonic.HarmonicView;
import com.hansson.dybalcalc.view.multiplane.MultiView;
import com.hansson.dybalcalc.view.tools.ToolsView;
import com.hansson.dybalcalc.view.singleplane.SingleView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {
    SINGLEPLANE("单面平衡", SingleView.class,FontAwesome.HOME, true), DOUBLEPLANE(
            "双面平衡",DoubleView.class, FontAwesome.BAR_CHART_O, false), MULTIPLANE(
            "多面平衡", MultiView.class,FontAwesome.TABLE, false), HARMONIC(
            "谐分量-影响系数法", HarmonicView.class,FontAwesome.FILE_TEXT_O, true), TOOLS(
            "辅助工具", ToolsView.class,FontAwesome.CALENDAR_O, false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }


    public Resource getIcon() {
        return icon;
    }
    
    public Class<? extends View> getViewClass() {
        return viewClass;
    }
    
    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
