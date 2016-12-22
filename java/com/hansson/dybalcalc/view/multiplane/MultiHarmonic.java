package com.hansson.dybalcalc.view.multiplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.HanssonUI;
import com.hansson.dybalcalc.balcalc.calc.Harmonic_EffBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.HanssonChart;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.hansson.dybalcalc.view.doubleplane.DoubleChart;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
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
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class MultiHarmonic extends CustomComponent {
	private boolean ta;
	private ComboBox comboBoxa;
	private TableCom tableComA, tableComB, tableComC, tableComD;
	private String[] stra, strb, strc, strd;
	private  ArrayList <VectorBal> alist = new ArrayList<VectorBal>();
	private VectorBal[] initalA, initalB;
	private DoubleChart chartA, chartB;
	private String[] strChartA, strChartB;
	private HanssonChart harmonicChartA;
	private HanssonChart harmonicChartB;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	public static final String TITLEA = "双面平衡-平面A平衡块安装示意图";
	public static final String TITLEB = "双面平衡-平面B平衡块安装示意图";

	public MultiHarmonic() {
        setSizeFull();
        //setCaption("谐分量-影响系数法");
        VerticalLayout hatmonicCalc = new VerticalLayout();
        
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
        
        hatmonicCalc.addComponent(input);
        hatmonicCalc.addComponent(button);
        hatmonicCalc.addComponent(result);
        hatmonicCalc.addComponent(chartButton);
        setCompositionRoot(hatmonicCalc);
        
		initalA =new VectorBal[]{new VectorBal(0,0),new VectorBal(0,0)};
		strChartA=new String[] {"试加重量Ta(g):", "校正质量Qa(g):"};
		harmonicChartA = new HanssonChart(TITLEA, initalA, strChartA);
		chartA =new DoubleChart(harmonicChartA);
		
		initalB =new VectorBal[]{new VectorBal(0,0),new VectorBal(0,0)};
		strChartB=new String[] {"试加重量Tb(g):", "校正质量Qb(g):"};
		harmonicChartB = new HanssonChart(TITLEB, initalB, strChartB);
		chartB =new DoubleChart(harmonicChartB);
    }

	private Component buildChartButton() {
		final Button reset = new Button("平面A平衡块安装示意图");
		reset.addStyleName(ValoTheme.BUTTON_PRIMARY);
	    reset.addClickListener(new ClickListener() {
	          @Override
	          public void buttonClick(final ClickEvent event) {
	        	  chartA.close();
	        	  //(UI.getCurrent()).removeWindow(chartA);
	        	  chartA =new DoubleChart(harmonicChartA);
	        	  (UI.getCurrent()).addWindow(chartA);
	        	  chartA.bringToFront();
	          }
	    });  
	    final Button calc = new Button("平面B平衡块安装示意图");
		calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
		calc.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		    	      chartB.close();
		    	   //(UI.getCurrent()).removeWindow(chartB);
		        	  chartB =new DoubleChart(harmonicChartB);
		        	  (UI.getCurrent()).addWindow(chartB);
		        	  chartB.bringToFront();
		       }
		});
		HorizontalLayout chartButton = new HorizontalLayout(reset,calc);
		chartButton.setWidth("100%");
		chartButton.setExpandRatio(reset, 1);
		return chartButton;
	}

	private Component buildResult() {
		strc=new String[]{"原始振动正分量A0z:", "试加重正分量Tz(g):",
			      "试加重后正分量A1z:","正对称影响系数αz:",
			       "试加重正分量Qz(g):","平面A校正重量Qa(g):"};
		tableComC= new TableCom(strc,WIDTH,RO);

		strd=new String[]{"原始振动反分量A0f:", "试加重反分量Tf(g):",
			      "试加重后反分量A1f:","反对称影响系数αf:",
			       "试加重反分量Qf(g):","平面B校正重量Qb(g):"};
		tableComD= new TableCom(strd,WIDTH,RO);
		CssLayout tablelayout = new CssLayout(tableComC,tableComD);

		return tablelayout;
	}

	private Component buildInput() {
		stra=new String[]{"原 始 振 动 A0:", "平面A试加重(g):", "试加重后振动A1:"};
		tableComA= new TableCom(stra,WIDTH,NRO);

		strb=new String[]{"原 始 振 动 B0:", "平面B试加重(g):", "试加重后振动B1:"};
		tableComB= new TableCom(strb,WIDTH,NRO);
		CssLayout tablelayout = new CssLayout(tableComA,tableComB);

		return tablelayout;
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
		Label remove= new Label("是否去掉试重");
		comboBoxa = new ComboBox();
		comboBoxa.addItem("Yes");
		comboBoxa.addItem("No");
		comboBoxa.setStyleName(ValoTheme.TEXTFIELD_TINY);
		comboBoxa.setWidth("100px");
		comboBoxa.setValue("Yes");
		comboBoxa.setNullSelectionAllowed(false);
		HorizontalLayout comboBox = new HorizontalLayout(remove,comboBoxa);
		comboBox.setSpacing(true);
		HorizontalLayout button = new HorizontalLayout(reset,comboBox,calc);
		button.setWidth("100%");
		button.setExpandRatio(reset, 1);
		button.setExpandRatio(comboBox, 1);

		resultPanel.addComponent(button);
        return resultPanel;
    }
	
	protected void calc() {
		CheckInput check = new CheckInput();
		//check.settList(alist);
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
		}else{
		
		}
	}	
	
	protected void clear() {//clear all data;
		tableComA.clearTableCom();
		tableComB.clearTableCom();
		tableComC.clearTableCom();
		tableComD.clearTableCom();
	}
}
