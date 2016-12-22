package com.hansson.dybalcalc.view.doubleplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.DoublePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.component.VectorCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.hansson.dybalcalc.event.SetValue;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DoubleCalc extends CustomComponent {

    private ArrayList <VectorCom> list = new ArrayList<VectorCom>();
    private ArrayList <VectorBal> alist = new ArrayList<VectorBal>();
	private VectorCom veca, vecb, vecat, veca1, vecb1, vecbt, veca2, vecb2;

	private VectorCom veca11,vecb12,veca21,vecb22,vecQa,vecQb;
	private ComboBox comboBoxa, comboBoxb;
	private SetValue setValue;
	private boolean ta, tb;
	private String[] strc;
	private TableCom tableComC;
	private TableCom tableComD;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	
	public DoubleCalc() {
        setSizeFull();
        setCaption("双面平衡计算");
       VerticalLayout doubleCalc = new VerticalLayout();
        
        Panel input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setContent(buildInput());
        
        Panel button = new Panel("");
        button.setSizeFull();
        button.setStyleName("v-panel-caption");
        button.setContent(buildButton());
        
        Panel result = new Panel("计算结果");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
        result.setContent(buildResult());
        
        Panel chartButton = new Panel("");
        chartButton.setSizeFull();
        chartButton.setStyleName("v-panel-caption");
        chartButton.setContent(buildChartButton());
        
        doubleCalc.addComponent(input);
        doubleCalc.addComponent(button);
        doubleCalc.addComponent(result);
        doubleCalc.addComponent(chartButton);
        doubleCalc.setComponentAlignment(result, Alignment.MIDDLE_CENTER);
        setCompositionRoot(doubleCalc);
    }
    
	private Component buildChartButton() {
		final Button reset = new Button("平面A平衡块安装示意图");
		reset.addStyleName(ValoTheme.BUTTON_PRIMARY);
	    reset.addClickListener(new ClickListener() {
	          @Override
	          public void buttonClick(final ClickEvent event) {
	            	//DoubleChart(A);
	          }
	    });  
	    final Button calc = new Button("平面B平衡块安装示意图");
		calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
		calc.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		            //DoubleChart(B);
		       }
		});
		HorizontalLayout chartButton = new HorizontalLayout(reset,calc);
		chartButton.setWidth("100%");
		chartButton.setExpandRatio(reset, 1);
		return chartButton;
	}

	private Component buildButton() {
		VerticalLayout resultPanel = new VerticalLayout();
        Responsive.makeResponsive(resultPanel);
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
		
		comboBoxa = new ComboBox("A面试加重是否去掉");
		comboBoxa.addItem("Yes");
		comboBoxa.addItem("No");
		comboBoxa.setStyleName(ValoTheme.TEXTFIELD_TINY);
		comboBoxa.setWidth("80px");
		comboBoxa.setValue("Yes");
		comboBoxa.setNullSelectionAllowed(false);
				
		comboBoxb = new ComboBox("B面试加重是否去掉");
		comboBoxb.addItem("Yes");
		comboBoxb.addItem("No");
		comboBoxb.setStyleName(ValoTheme.TEXTFIELD_TINY);
		comboBoxb.setWidth("80px");
		comboBoxb.setValue("Yes");
		comboBoxb.setNullSelectionAllowed(false);
			    
		CssLayout comboBox = new CssLayout(reset,comboBoxa,comboBoxb,calc);
		resultPanel.addComponent(comboBox);
		resultPanel.setComponentAlignment(comboBox,  Alignment.MIDDLE_CENTER);
		resultPanel.setSpacing(false);
        return resultPanel;
	}

	private Component buildInput() {
		String[] strc={"原始振动A0:", "平面A试加重量Ta(g):", 
				       "加重Ta后振动A1:", "加重Ta后振动B1:"};
		tableComC= new TableCom(strc,WIDTH,NRO);

		String[] strd={"原始振动B0:", "平面A试加重量Tb(g):", 
			       "加重Tb后振动A2:", "加重Tb后振动B2:"};
		tableComD= new TableCom(strd,WIDTH,NRO);
		CssLayout tablelayout = new CssLayout(tableComC,tableComD);

		
		return tablelayout;
	}
	
	private Component buildResult() {
		String[] strc={"影响系数α11:", "影响系数α21:", "校核重量 Qa(g):"};
		tableComC= new TableCom(strc,WIDTH,RO);

		String[] strd={"影响系数α12:", "影响系数α22:", "校核重量 Qb(g):"};
		tableComD= new TableCom(strd,WIDTH,RO);
		CssLayout tablelayout = new CssLayout(tableComC,tableComD);

        return tablelayout;
    }
	
	protected void calc() {
		CheckInput check = new CheckInput();
		check.setvList(list);
		if(!check.checkVecValue()){
	        Notification notification = new Notification(
	                "错误信息");
	        notification
	                .setDescription("<span>参数输入错误，参数为空或输入格式错误！</span>");
	        notification.setHtmlContentAllowed(true);
	        notification.setStyleName("tray dark small closable login-help");
	        notification.setPosition(Position.MIDDLE_CENTER);
	        notification.setDelayMsec(20000);
	        notification.show(Page.getCurrent());
		}
		else{
			if(comboBoxa.isEmpty() ||comboBoxb.isEmpty() ){
		        Notification notification = new Notification(
		                "错误信息");
		        notification
		                .setDescription("<span>请选择运算符号！</span>");
		        notification.setHtmlContentAllowed(true);
		        notification.setStyleName("tray dark small closable login-help");
		        notification.setPosition(Position.MIDDLE_CENTER);
		        notification.setDelayMsec(20000);
		        notification.show(Page.getCurrent());
			}
			else{
				Double vAa0 = Double.valueOf(list.get(0).getAmp().getValue());
				Double vPa0 = Double.valueOf(list.get(0).getPha().getValue());
				Double vAb0 = Double.valueOf(list.get(1).getAmp().getValue());
				Double vPb0 = Double.valueOf(list.get(1).getPha().getValue());
				Double vAat = Double.valueOf(list.get(2).getAmp().getValue());
				Double vPat = Double.valueOf(list.get(2).getPha().getValue());
				Double vAa1 = Double.valueOf(list.get(3).getAmp().getValue());
				Double vPa1 = Double.valueOf(list.get(3).getPha().getValue());
				Double vAb1 = Double.valueOf(list.get(4).getAmp().getValue());
				Double vPb1 = Double.valueOf(list.get(4).getPha().getValue());
				Double vAbt = Double.valueOf(list.get(5).getAmp().getValue());
				Double vPbt = Double.valueOf(list.get(5).getPha().getValue());
				Double vAa2 = Double.valueOf(list.get(6).getAmp().getValue());
				Double vPa2 = Double.valueOf(list.get(6).getPha().getValue());
				Double vAb2 = Double.valueOf(list.get(7).getAmp().getValue());
				Double vPb2 = Double.valueOf(list.get(7).getPha().getValue());
				VectorBal va0 = new VectorBal(vAa0,vPa0);  
				VectorBal vb0 = new VectorBal(vAb0,vPb0);
				VectorBal vat = new VectorBal(vAat,vPat);
				VectorBal va1 = new VectorBal(vAa1,vPa1);  
				VectorBal vb1 = new VectorBal(vAb1,vPb1);
				VectorBal vbt = new VectorBal(vAbt,vPbt);
				VectorBal va2 = new VectorBal(vAa2,vPa2);  
				VectorBal vb2 = new VectorBal(vAb2,vPb2);
				alist.add(va0);
				alist.add(vb0);
				alist.add(vat);
				alist.add(va1);
				alist.add(vb1);
				alist.add(vbt);
				alist.add(va2);
				alist.add(vb2);
				
				if(comboBoxa.getValue()=="Yes"){
					ta=true;
				}
				else{
					ta=false;
				}
				if(comboBoxb.getValue()=="Yes"){
					tb=true;
				}
				else{
					tb=false;
				}
			    DoublePlaneBal dpb= new DoublePlaneBal(alist,ta,tb);
			    ArrayList <VectorBal> temp = dpb.doublePlaneCal();
		         for(int i=0;i<temp.size();i++){ 
		    	     temp.get(i).print();
		         }
			    veca11.setReadOnly(false);
				setValue = new SetValue(veca11, temp.get(0));
				setValue.setValue();
				veca11.setReadOnly(true);

				veca21.setReadOnly(false);
				setValue = new SetValue(veca21, temp.get(1));
				setValue.setValue();
				veca21.setReadOnly(true);
				
				vecb12.setReadOnly(false);
				setValue = new SetValue(vecb12, temp.get(2));
				setValue.setValue();
				vecb12.setReadOnly(true);
				
				vecb22.setReadOnly(false);
				setValue = new SetValue(vecb22, temp.get(3));
				setValue.setValue();
				vecb22.setReadOnly(true);
				
			    
				vecQa.setReadOnly(false);
				setValue = new SetValue(vecQa, temp.get(4));
				setValue.setValue();
				vecQa.setReadOnly(true);
			
				vecQb.setReadOnly(false);
				setValue = new SetValue(vecQb, temp.get(5));
				setValue.setValue();
				vecQb.setReadOnly(true);
			}
		}
	}

	protected void clear() {//clear all data;
		veca.clearIgnoreReadOnly();
		vecb.clearIgnoreReadOnly();
		vecat.clearIgnoreReadOnly();
		veca1.clearIgnoreReadOnly();
		vecb2.clearIgnoreReadOnly();
		vecbt.clearIgnoreReadOnly();
		veca2.clearIgnoreReadOnly();
		vecb2.clearIgnoreReadOnly();
		veca11.clearIgnoreReadOnly();
		vecb12.clearIgnoreReadOnly();
		veca21.clearIgnoreReadOnly();
		vecb22.clearIgnoreReadOnly();
		vecQa.clearIgnoreReadOnly();
		vecQb.clearIgnoreReadOnly();

	}
}
