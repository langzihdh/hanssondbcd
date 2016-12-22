package com.hansson.dybalcalc.view.singleplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.SinglePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.HanssonChart;
import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.component.TableText;
import com.hansson.dybalcalc.event.SetTextValue;
import com.hansson.dybalcalc.event.ValidateValue;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class SingleCalc extends CustomComponent {

	private TableCom tableComA, tableComB;
	private String[] str,stra,strb;
	private TableText[] ampA, phaA;
	private  HanssonChart singlechart;
	private CssLayout singleCalc;
	private Panel result;
	private VectorBal[] inital;
	public static final String WIDTH = "150px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	public static final String TITLE = "单面平衡-平衡块安装示意图";
	
	public SingleCalc() {
        setSizeFull();
        setCaption("单面平衡计算");
        Panel Single = new Panel("");
        Single.setSizeFull();
        //Single.setVisible(false);
        Single.setStyleName("v-panel-caption");
       // Single.setIcon(FontAwesome.EDIT);
       
        
        singleCalc = new CssLayout();
        CssLayout inpanel = new CssLayout();
        inpanel.addComponent(buildInput());
        inpanel.addComponent(buildResult());
        //singleCalc.setSizeFull();
        
        Panel input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setIcon(FontAwesome.EDIT);
        input.setContent(inpanel);
        
        Panel button = new Panel("");
        //button.setSizeFull();
        button.setStyleName("v-panel-caption");
        button.setContent(buildButton());
        
        result = new Panel("");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
		inital =new VectorBal[]{new VectorBal(0,0),new VectorBal(0,0)};
		str=new String[] {"试加重量Ta(g):", "校正质量(g):"};
		singlechart = new HanssonChart(TITLE, inital, str);
        result.setContent(singlechart);
        
        singleCalc.addComponent(input);
        singleCalc.addComponent(buildButton());
        singleCalc.addComponent(result);
        Single.setContent(singleCalc);
        setCompositionRoot(Single);
    }
    
	private Component buildButton() {
		final Button reset = new Button("重  置");
		reset.setIcon(FontAwesome.ERASER);
		//reset.addStyleName(ValoTheme.BUTTON_DANGER);
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
		//button.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		button.setWidth("100%");
		button.setExpandRatio(reset, 1);
		return button;
	}

	private Component buildInput() {
		stra=new String[] {"原始振动 A0:", "试加重量Ta(g):", "试重后振动A1:"};
		tableComA= new TableCom(stra,WIDTH,NRO);
		ampA=tableComA.getVecAmp();
		phaA=tableComA.getVecPha();
        return tableComA;
    }
	
	private Component buildResult() {

		strb=new String[]{"影 响 系 数 α:", "加重(去试重)(g):", "加重(留试重)(g):"};
		tableComB= new TableCom(strb,WIDTH,RO);
        tableComB.getVecAmp();
		tableComB.getVecPha();
        return tableComB;
    }
	
	protected void calc() {
		ValidateValue vv= new ValidateValue(tableComA,stra);
		
		if(!vv.validateValue()){
	        Notification.show("错误信息", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}else{
			VectorBal vec[]= new VectorBal[stra.length];
			vec=tableComA.getTableComValue();
			SinglePlaneBal temp= new SinglePlaneBal(vec[0] ,vec[1],vec[2]);
			temp.SinglePlaneCal(); 
			ArrayList <VectorBal> list = temp.getList();
			
			VectorBal vecB[]=new VectorBal[strb.length];
			for(int i=0; i<strb.length; i++){
				vecB[i]= list.get(i);
			}
		    tableComB.setTableComValue(vecB);
			
			VectorBal vecCal1= list.get(1).format() ;
			VectorBal vecCal2= list.get(2).format() ;

			inital =new VectorBal[]{vec[1],vecCal1,vecCal2};
			str=new String[] {"试加重量Ta(g):", "加重(去试重)(g):", "加重(不去试重)(g):"};
			singlechart = new HanssonChart(TITLE, inital, str);
			result.setContent(singlechart);
			}
	}

	protected void clear() {//clear all data;
		tableComA.clearTableCom();
		tableComB.clearTableCom();
	}
}
