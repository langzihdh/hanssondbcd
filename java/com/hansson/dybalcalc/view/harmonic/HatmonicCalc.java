package com.hansson.dybalcalc.view.harmonic;

import java.math.BigDecimal;

import com.hansson.dybalcalc.balcalc.calc.SinglePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.event.CheckInput;
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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class HatmonicCalc extends CustomComponent {

    private TextField veca, vecb, dega, degb, vect, degt;
    private TextField vecaf, degaf, vecqr, degqr, degq, vecq;
	private double amp, pha;
	private ComboBox comboBoxa;
	private TableCom tableComA, tableComB, tableComC, tableComD;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;

	public HatmonicCalc() {
        setSizeFull();
        setCaption("谐分量-影响系数法");
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

	private Component buildResult() {
		String[] strc={"原始振动正分量A0z:", "试加重正分量Tz(g):",
			      "试加重后正分量A1z:","正对称影响系数αz:",
			       "试加重正分量Qz(g):","平面A校正重量Qa(g):"};
		tableComC= new TableCom(strc,WIDTH,RO);

		String[] strd={"原始振动反分量A0f:", "试加重反分量Tf(g):",
			      "试加重后反分量A1f:","反对称影响系数αf:",
			       "试加重反分量Qf(g):","平面B校正重量Qb(g):"};
		tableComD= new TableCom(strd,WIDTH,RO);
		CssLayout tablelayout = new CssLayout(tableComC,tableComD);

		return tablelayout;
	}

	private Component buildInput() {
		String[] stra={"原 始 振 动 A0:", "平面A试加重(g):", "试加重后振动A1:"};
		tableComA= new TableCom(stra,WIDTH,NRO);

		String[] strb={"原 始 振 动 B0:", "平面B试加重(g):", "试加重后振动B1:"};
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
			Double vAa = Double.valueOf(veca.getValue());
			Double vPa = Double.valueOf(dega.getValue());
			Double vAt = Double.valueOf(vect.getValue());
			Double vPt = Double.valueOf(degt.getValue());
			Double vAb = Double.valueOf(vecb.getValue());
			Double vPb = Double.valueOf(degb.getValue());
			VectorBal v1 = new VectorBal(vAa,vPa);  
			VectorBal v2 = new VectorBal(vAb,vPb);
			VectorBal vt = new VectorBal(vAt,vPt);
			
			SinglePlaneBal temp= new SinglePlaneBal(v1,v2,vt);
			temp.SinglePlaneCal();
			VectorBal vecCal1 = temp.getList().get(1);
			VectorBal vecCal2 = temp.getList().get(2);
			VectorBal vecEf = temp.getList().get(0);
			BigDecimal Efa = new BigDecimal(vecEf.getamp());  
	        amp = Efa.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
	        BigDecimal Efp = new BigDecimal(vecEf.getpha()); 
	        pha = Efp.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			vecaf.setReadOnly(false);
			vecaf.setValue(String.valueOf(amp));
			vecaf.setReadOnly(true);
			degaf.setReadOnly(false);
			degaf.setValue(String.valueOf(pha));
			degaf.setReadOnly(true);
			
			BigDecimal cal2a = new BigDecimal(vecCal2.getamp());  
	        amp = cal2a.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
	        BigDecimal cal2p = new BigDecimal(vecCal2.getpha()); 
	        pha = cal2p.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			vecq.setReadOnly(false);
			vecq.setValue(String.valueOf(amp));
			vecq.setReadOnly(true);
			degq.setReadOnly(false);
			degq.setValue(String.valueOf(pha));
			degq.setReadOnly(true);
			
			BigDecimal cal1a = new BigDecimal(vecCal1.getamp());  
	        amp = cal1a.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
	        BigDecimal cal1p = new BigDecimal(vecCal1.getpha()); 
	        pha = cal1p.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			vecqr.setReadOnly(false);
			vecqr.setValue(String.valueOf(amp));
			vecqr.setReadOnly(true);
			degqr.setReadOnly(false);
			degqr.setValue(String.valueOf(pha));
			degqr.setReadOnly(true);

		}
	}

	protected void clear() {//clear all data;
		veca.clear();
		dega.clear();
		vecb.clear();
		degb.clear();
		vect.clear();
		degt.clear();
		vecaf.setReadOnly(false);
		vecaf.clear();
		vecaf.setReadOnly(true);
		degaf.setReadOnly(false);
		degaf.clear();
		degaf.setReadOnly(true);
		vecqr.setReadOnly(false);
		vecqr.clear();
		vecqr.setReadOnly(true);
		degqr.setReadOnly(false);
		degqr.clear();
		degqr.setReadOnly(true);
		vecq.setReadOnly(false);
		vecq.clear();
		vecq.setReadOnly(true);
		degq.setReadOnly(false);
		degq.clear();
		degq.setReadOnly(true);
	}
}
