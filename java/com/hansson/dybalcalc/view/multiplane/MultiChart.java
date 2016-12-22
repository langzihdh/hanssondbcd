package com.hansson.dybalcalc.view.multiplane;

import com.hansson.dybalcalc.component.HanssonChart;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class MultiChart extends Window {
	
	public MultiChart(HanssonChart multiChart){
		//setCaption(harmonicChart.getCaption());
		multiChart.getHeight();
		int h= UI.getCurrent().getPage().getBrowserWindowHeight()/2;
		int w= UI.getCurrent().getPage().getBrowserWindowWidth()/2;
  		setWidth(String.valueOf(w));
  		//setWidth(String.valueOf(h));
  		setHeight(String.valueOf(h));
		setDraggable(true);
		center();
		setContent(multiChart);
		
	}

}
