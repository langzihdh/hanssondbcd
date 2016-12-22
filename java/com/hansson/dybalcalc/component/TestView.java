package com.hansson.dybalcalc.component;

import java.util.HashSet;
import java.util.Set;


import org.vaadin.ui.NumberField;

import com.hansson.dybalcalc.view.doubleplane.DoubleCoeff;
import com.hansson.dybalcalc.view.doubleplane.DoubleHatmonic;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout.SpacingHandler;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;




@SuppressWarnings("serial")
public class TestView extends CustomComponent {
	private int defaultColumnWidth = 300;
    private Set<SpacingHandler> spacingHandlers = new HashSet<>();
    
	public TestView(){
		setCaption("双面平衡计算");
		TabSheet tabsheet = new TabSheet();
		
		//layout.addComponent(tabsheet);
		VerticalLayout harmanic = new VerticalLayout();
		harmanic.setSizeFull();
		VerticalLayout coeff = new VerticalLayout();
		coeff.setSizeFull();
		tabsheet.addTab(harmanic, "谐分量-影响系数法",null);
		tabsheet.addTab(coeff, "影响系数法",null);
		
		harmanic.addComponent(new DoubleHatmonic());
		coeff.addComponent(new DoubleCoeff());
		
        Panel input = new Panel("");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setContent(tabsheet);
        
        setSizeFull();
	    setCompositionRoot(input);  
	}

}
