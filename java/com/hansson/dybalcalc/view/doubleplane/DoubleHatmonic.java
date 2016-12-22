package com.hansson.dybalcalc.view.doubleplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.HanssonUI;
import com.hansson.dybalcalc.balcalc.calc.Harmonic_EffBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.HanssonChart;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.hansson.dybalcalc.event.ValidateValue;
import com.vaadin.server.FontAwesome;
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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DoubleHatmonic extends CustomComponent {
	private boolean ta;
	private NativeSelect comboBoxa;
	private TableCom tableComA, tableComB, tableComC, tableComD;
	private String[] stra, strb, strc, strd;

	private VectorBal[] initalA, initalB;
	private DoubleChart chartA, chartB;
	private String[] strChartA, strChartB;
	private HanssonChart harmonicChartA;
	private HanssonChart harmonicChartB;
	private VerticalLayout hatmonicCalc;
	private VectorBal[] vecA;
	private VectorBal[] vecB;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	public static final String TITLEA = "双面平衡-平面A平衡块安装示意图";
	public static final String TITLEB = "双面平衡-平面B平衡块安装示意图";

	public DoubleHatmonic() {
        setSizeFull();
        //setCaption("谐分量-影响系数法");
        hatmonicCalc = new VerticalLayout();
        
        Panel input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setIcon(FontAwesome.EDIT);
        input.setContent(buildInput());
        
        Panel button = new Panel("");
        button.setSizeFull();
        button.setStyleName("v-panel-caption");
        button.setContent(buildButton());
        
        Panel result = new Panel("计算结果");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
        result.setIcon(FontAwesome.DATABASE);
        result.setContent(buildResult());
        
        Panel chartButton = new Panel("");
        chartButton.setSizeFull();
        chartButton.setStyleName("v-panel-caption");
        chartButton.setContent(buildChartButton());
        
        hatmonicCalc.addComponent(input);
        hatmonicCalc.addComponent(buildButton());
        hatmonicCalc.addComponent(result);
        hatmonicCalc.addComponent(buildChartButton());
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
		final Button drawA = new Button("平面A平衡块安装示意图");
		drawA.addStyleName(ValoTheme.BUTTON_PRIMARY);
		drawA.setIcon(FontAwesome.YELP);
		drawA.addClickListener(new ClickListener() {
	          @Override
	          public void buttonClick(final ClickEvent event) {
	        	  chartA.close();
	        	  //(UI.getCurrent()).removeWindow(chartA);
	        	  chartA =new DoubleChart(harmonicChartA);
	        	  (UI.getCurrent()).addWindow(chartA);
	        	  chartA.bringToFront();
	          }
	    });  
	    final Button drawB = new Button("平面B平衡块安装示意图");
	    drawB.addStyleName(ValoTheme.BUTTON_PRIMARY);
	    drawB.setIcon(FontAwesome.YELP);
	    drawB.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		    	      chartB.close();
		    	   //(UI.getCurrent()).removeWindow(chartB);
		        	  chartB =new DoubleChart(harmonicChartB);
		        	  (UI.getCurrent()).addWindow(chartB);
		        	  chartB.bringToFront();
		       }
		});
		HorizontalLayout chartButton = new HorizontalLayout(drawA,drawB);
		chartButton.setWidth("100%");
		chartButton.setExpandRatio(drawA, 1);
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

		vecA=new VectorBal[stra.length];
		vecB=new VectorBal[strb.length];

		return tablelayout;
	}
	
	private Component buildButton() {
		VerticalLayout resultPanel = new VerticalLayout();
        Responsive.makeResponsive(resultPanel);
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
		Label remove= new Label("是否去掉试重");
		comboBoxa = new NativeSelect();
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
		ArrayList <VectorBal> alist = new ArrayList<VectorBal>();
		ValidateValue vva= new ValidateValue(tableComA,stra);
		ValidateValue vvb= new ValidateValue(tableComA,stra);
		if(!vva.validateValue() && !vvb.validateValue()){
	        Notification.show("错误信息", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}else{
			vecA=tableComA.getTableComValue();
			vecB=tableComB.getTableComValue();
			for(int i=0; i<stra.length; i++){
				alist.add(vecA[i]);
				alist.add(vecB[i]);
			}
			if(comboBoxa.getValue()=="Yes"){
				ta=true;
			}
			else{
				ta=false;
			}

			Harmonic_EffBal dpb= new Harmonic_EffBal(alist,ta);
		    dpb.harmonicCal();
	          for(int i=0;i<12;i++){ 
	        	  //dpb.getHar().get(i).print();; 
	          } 
			VectorBal vecC[]=new VectorBal[strc.length];
			VectorBal vecD[]=new VectorBal[strd.length];
			for(int i=0; i<strc.length; i++){
				vecC[i]= dpb.getHar().get(i);
				
			}
			for(int i=0; i<strd.length; i++){
				vecD[i]= dpb.getHar().get(i+strc.length);
			}
		    tableComC.setTableComValue(vecC);
		    tableComD.setTableComValue(vecD);
		    setCompositionRoot(hatmonicCalc);
		    
			VectorBal vecQa= vecC[5].format() ;
			VectorBal vecQb= vecD[5].format() ;
			initalA =new VectorBal[]{vecA[1],vecQa};
			harmonicChartA = new HanssonChart(TITLEA, initalA, strChartA);
			
			initalB =new VectorBal[]{vecB[1],vecQb};
			harmonicChartB = new HanssonChart(TITLEA, initalB, strChartB);
		}
	}	
	
	protected void clear() {//clear all data;
		tableComA.clearTableCom();
		tableComB.clearTableCom();
		tableComC.clearTableCom();
		tableComD.clearTableCom();
	}
}
