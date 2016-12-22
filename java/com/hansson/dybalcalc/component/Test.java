package com.hansson.dybalcalc.component;

import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class Test extends CustomComponent {

    private TextField veca;
	private TextField vecb;
	private TextField vecc;
	private TextField dega;
	private TextField degb;
	private TextField degc;
	private TextField vecd;
	private TextField degd;

	public Test() {
        setSizeFull();
        setCaption("矢量正反对称分解");

        Panel panel2 = new Panel("");
        //panel2.addStyleName("mypanelexample");
        panel2.setSizeFull();
        //panel2.setSizeUndefined();
        panel2.setStyleName("v-panel-caption");
        
        Component loginForm1 = buildResultForm();
        panel2.setContent(loginForm1);
        setCompositionRoot(panel2);
        //setComponentAlignment(panel2, Alignment.MIDDLE_CENTER);
    }
    
	private Component buildResultForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        
		Label va = new Label("矢量A:");
		veca = new TextField("振幅");
		veca.setWidth("120px");
		veca.setStyleName(ValoTheme.TEXTFIELD_TINY);
		Label vaa = new Label("μm  ∠");
		dega = new TextField("相位");
		dega.setWidth("120px");
		dega.setStyleName(ValoTheme.TEXTFIELD_TINY);
		Label da = new Label("°");
		da.setSizeUndefined();
	    HorizontalLayout inputforma = new HorizontalLayout(va,veca,vaa,dega,da);
	    inputforma.setSpacing(true);
	    inputforma.setComponentAlignment(va, Alignment.BOTTOM_RIGHT);
	    inputforma.setComponentAlignment(vaa, Alignment.BOTTOM_RIGHT);
	    inputforma.setComponentAlignment(da, Alignment.MIDDLE_CENTER);
	    inputforma.setSizeUndefined();

	    
		
		Label vb = new Label("矢量B:");
		vecb = new TextField("振幅");
		vecb.setWidth("120px");
		vecb.setStyleName(ValoTheme.TEXTFIELD_TINY);
		Label vab = new Label("μm  ∠");
		degb = new TextField("相位");
		degb.setWidth("120px");
		degb.setStyleName(ValoTheme.TEXTFIELD_TINY);
		Label db = new Label("°");
		db.setSizeUndefined();
	    HorizontalLayout inputformb = new HorizontalLayout(vb,vecb,vab,degb,db);
	    inputformb.setSpacing(true);
	    inputformb.setComponentAlignment(vb, Alignment.BOTTOM_RIGHT);
	    inputformb.setComponentAlignment(vab, Alignment.BOTTOM_RIGHT);
	    inputformb.setComponentAlignment(db, Alignment.MIDDLE_CENTER);
	    inputformb.setSizeUndefined();


		 Label vc = new Label("正对称分量Z:");
		 vecc = new TextField("振幅");
		 vecc.setWidth("100px");
		 vecc.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 Label vac = new Label("μm  ∠");
		 degc = new TextField("相位");
		 degc.setWidth("100px");
		 degc.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 Label dc = new Label("°");
		 dc.setSizeUndefined();
	     HorizontalLayout inputformc = new HorizontalLayout(vc,vecc,vac,degc,dc);
	     inputformc.setSpacing(true);
	     inputformc.setComponentAlignment(vc, Alignment.BOTTOM_RIGHT);
	     inputformc.setComponentAlignment(vac, Alignment.BOTTOM_RIGHT);
	     inputformc.setComponentAlignment(dc, Alignment.MIDDLE_CENTER);
	     inputformc.setSizeUndefined();
	     
		 Label vd = new Label("反对称分量F:");
		 vecd = new TextField("振幅");
		 vecd.setWidth("100px");
		 vecd.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 Label vad = new Label("μm  ∠");
		 degd = new TextField("相位");
		 degd.setWidth("100px");
		 degd.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 Label dd = new Label("°");
		 dd.setSizeUndefined();
	     HorizontalLayout inputformd = new HorizontalLayout(vd,vecd,vad,degd,dd);
	     inputformd.setSizeUndefined();
	     inputformd.setSpacing(true);
	     inputformd.setComponentAlignment(vd, Alignment.BOTTOM_RIGHT);
	     inputformd.setComponentAlignment(vad, Alignment.BOTTOM_RIGHT);
	     inputformd.setComponentAlignment(dd, Alignment.MIDDLE_CENTER);

	     final Button reset = new Button("重  置");
	     reset.addClickListener(new ClickListener() {
	            @Override
	            public void buttonClick(final ClickEvent event) {
	            	clear();
	            }
	        });  
	     final Button calc = new Button("计算");
		        calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
		        calc.addClickListener(new ClickListener() {
		            @Override
		        public void buttonClick(final ClickEvent event) {
		            calc();
		        }
		    });
		HorizontalLayout button = new HorizontalLayout(reset,calc);
		//button.addStyleName("dashboard-panel-toolbar");
		button.setWidth("100%");
		loginPanel.addComponent(inputforma);
		loginPanel.addComponent(inputformb);
//		loginPanel.addComponent(reset);
		loginPanel.addComponent(inputformc);
		loginPanel.addComponent(inputformd);
		loginPanel.addComponent(button);
		button.setExpandRatio(reset, 1);
		//loginPanel.setComponentAlignment(reset, Alignment.MIDDLE_CENTER);
		//loginPanel.setComponentAlignment(calc, Alignment.MIDDLE_CENTER);
        //loginPanel.setSizeUndefined();

        Responsive.makeResponsive(loginPanel);
        //loginPanel.addStyleName("login-panel");

        return loginPanel;
    }

    protected void calc() {
		// TODO Auto-generated method stub
		
	}

	protected void clear() {
		// TODO Auto-generated method stub
		
	}
}
