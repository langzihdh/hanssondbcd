package com.hansson.dybalcalc.view.singleplane;

import com.hansson.dybalcalc.view.doubleplane.DoubleCoeff;
import com.hansson.dybalcalc.view.doubleplane.DoubleHatmonic;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class SingleTabView extends CustomComponent {
    
	public SingleTabView(){
		setCaption("单面平衡计算");
		TabSheet tabsheet = new TabSheet();
		
		VerticalLayout singleCalc = new VerticalLayout();
		singleCalc.setSizeFull();
		VerticalLayout coeff = new VerticalLayout();
		//coeff.setSizeFull();
		//tabsheet.addTab(harmanic, "谐分量-影响系数法",null);
		tabsheet.addTab(coeff, "影响系数法",null);
		//tabsheet.getTab(harmanic).setIcon(FontAwesome.BALANCE_SCALE);
		tabsheet.getTab(coeff).setIcon(FontAwesome.GEARS);
		//harmanic.addComponent(new DoubleHatmonic());
		coeff.addComponent(new SingleCalc());
		
        Panel input = new Panel("");
        //input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setContent(tabsheet);
        //tabsheet.setHeight("100%");
        setSizeFull();
	    setCompositionRoot(input);  
	}

}
