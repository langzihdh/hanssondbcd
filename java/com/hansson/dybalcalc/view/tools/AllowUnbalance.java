package com.hansson.dybalcalc.view.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.AllowUnbalCal;
import com.hansson.dybalcalc.component.TextCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.hansson.dybalcalc.event.SetValue;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class  AllowUnbalance extends CustomComponent{

    private TextCom m, n, r;
	private NativeSelect g;
	private TextCom eper, uper, tw3, tw4, tw5;
	private ArrayList <TextCom> list = new ArrayList<TextCom>();
	private CheckInput check;
	private BigDecimal bigDec;
	private String value;
	
	public AllowUnbalance() {
		 setCaption("许用不平衡量计算");
		 setSizeFull();
		 VerticalLayout fields = new VerticalLayout();
		 fields.setSpacing(false);
		 fields.setSizeFull();
		 fields.addComponent(buildInput());
		 fields.addComponent(buildResult());
		 setCompositionRoot(fields);
	}

	private Component buildResult() {
		 Panel result = new Panel("计算结果");
		 result.addStyleName("mypanelexample");
		 result.setStyleName("v-panel-caption");
		 
		 eper = new TextCom("许用不平衡度：", "(g.mm/kg)", "150px", true);
		 uper = new TextCom("许用不平衡量：", "(g.mm)", "150px", true);
		 eper.getText().setReadOnly(true);
		 uper.getText().setReadOnly(true);
		 final Button reset = new Button("重  置");
		 reset.setIcon(FontAwesome.ERASER);
		 reset.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		            	clear();
		       }
		 });  
		 final Button calc = new Button("计算");
			   calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
			   calc.setIcon(FontAwesome.CALCULATOR);
			   calc.addClickListener(new ClickListener() {
			   @Override
			   public void buttonClick(final ClickEvent event) {
			            calc();
			    }
	     });
	     HorizontalLayout button = new HorizontalLayout(reset,calc);
	     button.setWidth("100%");

	     final VerticalLayout resultPanel = new VerticalLayout(eper, uper);
	     resultPanel.setComponentAlignment(eper, Alignment.MIDDLE_CENTER);
	     resultPanel.setComponentAlignment(uper, Alignment.MIDDLE_CENTER);
	     resultPanel.setSpacing(false);
		 resultPanel.addComponent(button);
		 resultPanel.setStyleName("v-panel-caption");
		 button.setExpandRatio(reset, 1);
	     Responsive.makeResponsive(resultPanel);
	     result.setContent(resultPanel);
	     return result;
	}

	private Component buildInput() {
		 Panel input = new Panel("数据输入");
		 //input.addStyleName("mypanelexample");
		 input.setStyleName("v-panel-caption");
		 input.setIcon(FontAwesome.EDIT);
		 
		 m = new TextCom("转子重量：", "(kg)", "150px", false);
		 n = new TextCom("平衡转速：", "(rpm)", "150px", false);
		 r = new TextCom("加重半径:", "(mm)", "150px", false);
		 g = new NativeSelect("平衡等级");
		 g.addItems("G0.4", "G1","G2.5","G6.3",
				 "G16","G40","G100","G250","G630","G1600","G400");
	     g.setNullSelectionAllowed(false);
	     g.setValue("G6.3");
	     g.setImmediate(true);
		 g.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 list.add(m);
		 list.add(n);
		 list.add(r);
		 HorizontalLayout mn = new HorizontalLayout(m,n);
		 HorizontalLayout rg = new HorizontalLayout(r,g);
		 mn.setSpacing(true);
		 rg.setSpacing(true);

	     final VerticalLayout inputPanel = new VerticalLayout(mn,rg);
	     inputPanel.setSpacing(false);
	     inputPanel.setSizeFull();
	     input.setContent(inputPanel);
		 check = new CheckInput();
		 check.settList(list);
		 return input;
	}

	protected void calc() {
		double vm, vn, va, vr, vg;
		if(!check.checkTxtValue()){
	        Notification.show("错误信息", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}else{

			if(g.isEmpty()){
		        Notification.show("错误信息", "请选择平衡精度！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else{
				vg = geValueG();
				vm = Double.valueOf(m.getText().getValue());
				vn = Double.valueOf(n.getText().getValue());
				vr = Double.valueOf(r.getText().getValue());
				AllowUnbalCal tw= new AllowUnbalCal(vm,vn,vr,vg);
				tw.calcEper();
				tw.calcUper();
				
			    value = new DecimalFormat("#.##").format(tw.getEper());
			    eper.getText().setValueIgnoreReadOnly(value);
			    value = new DecimalFormat("#.##").format(tw.getUper());
			    uper.getText().setValueIgnoreReadOnly(value);
			}
		}
	}

	private double geValueG() {
	    double value=0;
		switch (g.getValue().toString()) {
        case "G0.4":
        	value=0.4;
            break;
        case "G1":
        	value=1;
            break;
        case "G2.5":
        	value=2.5;
            break;
        case "G6.3":
        	value=6.3;
            break;
        case "G16":
        	value=16;
            break;
        case "G40":
        	value=40;
            break;
        case "G100":
        	value=100;
            break;
        case "G250":
        	value=250;
            break;
        case "G630":
        	value=630;
            break;
        case "G1600":
        	value=1600;
            break;
        case "G4000":
        	value=4000;
            break;
        }
		return value;
	}

	protected void clear() {
		 m.clearIgnoreReadOnly();
		 n.clearIgnoreReadOnly();
		 r.clearIgnoreReadOnly();
		 eper.clearIgnoreReadOnly();
		 uper.clearIgnoreReadOnly();
	}
}
