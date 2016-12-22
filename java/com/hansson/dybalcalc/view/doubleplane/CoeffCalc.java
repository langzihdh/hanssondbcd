package com.hansson.dybalcalc.view.doubleplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.DoublePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.HanssonChart;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.component.VectorCom;
import com.hansson.dybalcalc.event.CheckInput;
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
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class CoeffCalc extends CustomComponent {

    private ArrayList <VectorCom> list = new ArrayList<VectorCom>();
    private ArrayList <VectorBal> alist = new ArrayList<VectorBal>();
	private VectorBal[] initalA, initalB;
	private String[] strChartA, strChartB;
	private HanssonChart coeffChartA;
	private HanssonChart coeffChartB;
	private ComboBox comboBoxa, comboBoxb;
	private boolean ta, tb;
	private TableCom tableComA, tableComB, tableComC, tableComD;
	private String[] stra, strb, strc, strd;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	public static final String TITLEA = "双面平衡-A面平衡块安装示意图";
	public static final String TITLEB = "双面平衡-B面平衡块安装示意图";
	
	public CoeffCalc() {
        setSizeFull();
        //setCaption("双面平衡计算");
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
        
		initalA =new VectorBal[]{new VectorBal(0,0),new VectorBal(0,0)};
		strChartA=new String[] {"试加重量Ta(g):", "校正质量Qa(g):"};
		coeffChartA = new HanssonChart(TITLEA, initalA, strChartA);
		
		initalB =new VectorBal[]{new VectorBal(0,0),new VectorBal(0,0)};
		strChartB=new String[] {"试加重量Tb(g):", "校正质量Qb(g):"};
		coeffChartB = new HanssonChart(TITLEB, initalB, strChartB);
    }
    
	private Component buildChartButton() {
		final Button reset = new Button("平面A平衡块安装示意图");
		reset.addStyleName(ValoTheme.BUTTON_PRIMARY);
	    reset.addClickListener(new ClickListener() {
	          @Override
	          public void buttonClick(final ClickEvent event) {
	        	  Window chartA =new DoubleChart(coeffChartA);
	        	  (UI.getCurrent()).addWindow(chartA);
	        	  chartA.bringToFront();
	          }
	    });  
	    final Button calc = new Button("平面B平衡块安装示意图");
		calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
		calc.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		        	  Window chartB =new DoubleChart(coeffChartB);
		        	  (UI.getCurrent()).addWindow(chartB);
		        	  chartB.bringToFront();
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
		stra=new String[]{"原始振动A0:", "平面A试加重量Ta(g):", 
				       "加重Ta后振动A1:", "加重Ta后振动B1:"};
		tableComA= new TableCom(stra,WIDTH,NRO);

		strb=new String[]{"原始振动B0:", "平面B试加重量Tb(g):", 
			       "加重Tb后振动A2:", "加重Tb后振动B2:"};
		tableComB= new TableCom(strb,WIDTH,NRO);
		
		CssLayout tablelayout = new CssLayout(tableComA,tableComB);
		return tablelayout;
	}
	
	private Component buildResult() {
        strc=new String[]{"影响系数α11:", "影响系数α21:", "校核重量 Qa(g):"};
		tableComC= new TableCom(strc,WIDTH,RO);

		strd=new String[]{"影响系数α12:", "影响系数α22:", "校核重量 Qb(g):"};
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
				VectorBal vecA[]=new VectorBal[stra.length];
				VectorBal vecB[]=new VectorBal[strb.length];
				vecA=tableComA.getTableComValue();
				vecB=tableComB.getTableComValue();
				
				alist.add(vecA[0]);
				alist.add(vecB[0]);
				alist.add(vecA[1]);
				alist.add(vecA[2]);
				alist.add(vecA[3]);
				alist.add(vecB[1]);
				alist.add(vecB[2]);
				alist.add(vecB[3]);
				
				
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
				VectorBal vecC[]=new VectorBal[strc.length];
				VectorBal vecD[]=new VectorBal[strd.length];
				for(int i=0; i<strc.length; i++){
					vecC[i]= temp.get(i);
				}
				for(int i=0; i<strd.length; i++){
					vecD[i]= temp.get(i+strc.length);
				}
			    tableComC.setTableComValue(vecC);
			    tableComD.setTableComValue(vecD);
			    
				VectorBal vecQa= vecC[2].format() ;
				VectorBal vecQb= vecD[2].format() ;
				initalA =new VectorBal[]{vecA[1],vecQa};
				coeffChartA = new HanssonChart(TITLEA, initalA, strChartA);
				
				initalB =new VectorBal[]{vecB[1],vecQb};
				coeffChartB = new HanssonChart(TITLEA, initalB, strChartB);
			}
		}
	}

	protected void clear() {//clear all data;
		tableComA.clearTableCom();
		tableComB.clearTableCom();
		tableComC.clearTableCom();
		tableComD.clearTableCom();
	}
}
