package com.hansson.dybalcalc.view.menu;


import com.hansson.dybalcalc.view.doubleplane.DoubleView;
import com.hansson.dybalcalc.view.help.HelpView;
import com.hansson.dybalcalc.view.multiplane.MultiView;
import com.hansson.dybalcalc.view.singleplane.SingleView;
import com.hansson.dybalcalc.view.tools.ToolsView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum HanssonViewType {
    SINGLEPLANE("单面平衡", SingleView.class,FontAwesome.HOME, true), DOUBLEPLANE(
            "双面平衡",DoubleView.class, FontAwesome.BALANCE_SCALE, false), MULTIPLANE(
            "多面平衡", MultiView.class,FontAwesome.TH_LARGE, false), TOOLS(
            "辅助工具", ToolsView.class,FontAwesome.CALENDAR_O, false), HELP(
                    "系统帮助", HelpView.class,FontAwesome.QUESTION_CIRCLE, true);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private HanssonViewType(final String viewName,
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
    
    public static HanssonViewType getByViewName(final String viewName) {
        HanssonViewType result = null;
        for (HanssonViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
