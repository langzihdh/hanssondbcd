package com.hansson.dybalcalc.view.doubleplane;

import com.hansson.dybalcalc.component.HanssonChart;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class DoubleChart extends Window {
	
	public DoubleChart(HanssonChart harmonicChart){
		//setCaption(harmonicChart.getCaption());
		harmonicChart.getHeight();
		int h= UI.getCurrent().getPage().getBrowserWindowHeight()/2;
		int w= UI.getCurrent().getPage().getBrowserWindowWidth()/2;
  		setWidth(String.valueOf(w));
  		//setWidth(String.valueOf(h));
  		setHeight(String.valueOf(h));
		setDraggable(true);
		center();
		setContent(harmonicChart);
		
	}

}
