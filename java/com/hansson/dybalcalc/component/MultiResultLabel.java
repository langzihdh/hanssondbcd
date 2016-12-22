package com.hansson.dybalcalc.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MultiResultLabel extends CustomComponent{
	private Label amp, pha;
	private VerticalLayout root;

	public MultiResultLabel(String strAmp, String strPha) {
		 setSizeFull();
		 root = new VerticalLayout();
	     amp = new Label(strAmp);
	     amp.setWidth("100");		 
		 pha = new Label(strPha);
		 pha.setWidth("100");
		 
		 root.addComponent(amp);
		 root.addComponent(pha);
		 root.setComponentAlignment(amp, Alignment.BOTTOM_RIGHT);
		 root.setComponentAlignment(pha, Alignment.BOTTOM_RIGHT);
		 root.setSpacing(true);
		 root.setSizeFull();
		 setCompositionRoot(root);  
	}
}
