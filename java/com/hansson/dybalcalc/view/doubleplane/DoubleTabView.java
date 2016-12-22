package com.hansson.dybalcalc.view.doubleplane;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;




@SuppressWarnings("serial")
public class DoubleTabView extends CustomComponent {
    
	public DoubleTabView(){
		setCaption("双面平衡计算");
		TabSheet tabsheet = new TabSheet();
		tabsheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
		VerticalLayout harmanic = new VerticalLayout();
		harmanic.setSizeFull();
		VerticalLayout coeff = new VerticalLayout();
		coeff.setSizeFull();
		tabsheet.addTab(harmanic, "谐分量-影响系数法",null);
		tabsheet.addTab(coeff, "影响系数法",null);
		tabsheet.getTab(harmanic).setIcon(FontAwesome.BALANCE_SCALE);
		tabsheet.getTab(coeff).setIcon(FontAwesome.GEARS);
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
