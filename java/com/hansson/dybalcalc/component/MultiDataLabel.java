package com.hansson.dybalcalc.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MultiDataLabel extends CustomComponent{
	private Label amp, pha;
	private VerticalLayout root;

	public MultiDataLabel(int tag) {
		 setSizeFull();
		 root = new VerticalLayout();
		 if(tag==0){
			 amp = new Label("原始振幅");
			 amp.setWidth("100");		 
			 pha = new Label("原始相位");
			 pha.setWidth("100");
		 }else{
			 amp = new Label("P"+tag+"加重后振幅");
			 amp.setWidth("100");
			 pha = new Label("P"+tag+"加重后相位");
			 pha.setWidth("100");
		 }
		 
		 root.addComponent(amp);
		 root.addComponent(pha);
		 root.setComponentAlignment(amp, Alignment.BOTTOM_RIGHT);
		 root.setComponentAlignment(pha, Alignment.BOTTOM_RIGHT);
		 root.setSpacing(true);
		 root.setSizeFull();
		 setCompositionRoot(root);  
	}
}
