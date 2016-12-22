package com.hansson.dybalcalc.view.multiplane;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.Guass;
import com.hansson.dybalcalc.balcalc.calc.LeastSquares;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.MultiDataTable;
import com.hansson.dybalcalc.component.MultiResultTable;
import com.hansson.dybalcalc.component.TextCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class MultiCoeff extends CustomComponent {

    private ArrayList <TextCom> list = new ArrayList<TextCom>();
	private MultiDataTable multiTable;
	private TextCom plane, points;
	private VerticalLayout multiCoeffCalc;
	private Panel input, button, result;
	private CheckInput check;
	private Integer pm, pn;
	private VectorBal[][] vecA1, af;
	private VectorBal[] vecA0, vecT, x,vecAR;
	private boolean[] checkBox;
	private MultiResultTable multiResultTable;
	public static final String WIDTH = "60px";
	public static final String WIDTH_RESULT = "80px";
	public static final boolean RO = true;
	public static final boolean NRO = false;
	public static final String TITLEA = "双面平衡-平面A平衡块安装示意图";
	public static final String TITLEB = "双面平衡-平面B平衡块安装示意图";
	
	public MultiCoeff() {
        setSizeFull();
        //setCaption("双面平衡计算");
        multiCoeffCalc = new VerticalLayout();
        
        Panel setmn = new Panel("平面数和测点数设置");
        setmn.setSizeFull();
        setmn.setStyleName("v-panel-caption");
        setmn.setIcon(FontAwesome.EDIT);
        setmn.setContent(buildSetMN());
        
        input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setIcon(FontAwesome.EDIT);
        
        button = new Panel("");
        button.setSizeFull();
        button.setStyleName("v-panel-caption");
        button.setContent(buildButton());
        
        result = new Panel("计算结果");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
        result.setIcon(FontAwesome.DATABASE);
        //result.setContent(buildResult());
        
        Panel chartButton = new Panel("");
        chartButton.setSizeFull();
        chartButton.setStyleName("v-panel-caption");

        multiCoeffCalc.addComponent(setmn);
        multiTable= new MultiDataTable();
        multiResultTable= new MultiResultTable();

        setCompositionRoot(multiCoeffCalc);
        
    }
    
	private Component buildSetMN() {
		plane = new TextCom("加重平面数M:", "(个)", "100px", false);
		plane.getText().setDecimalAllowed(false);
		plane.getText().setNegativeAllowed(false);
		points = new TextCom("振动测点数N:", "(个)", "100px", false);
		points.getText().setDecimalAllowed(false);
		points.getText().setNegativeAllowed(false);
		list.clear();
		list.add(plane);
		list.add(points);
	    final Button calc = new Button("确认");
		calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
	    calc.setIcon(FontAwesome.SAVE);
		calc.addClickListener(new ClickListener() {
		       @Override
		       public void buttonClick(final ClickEvent event) {
		    	   multiCoeffCalc.removeComponent(result);
		           buildTable();
		       }
		});
		
		HorizontalLayout mn = new HorizontalLayout(plane, points, calc);
		mn.setWidth("100%");
		mn.setComponentAlignment(calc,  Alignment.MIDDLE_RIGHT);
		return mn;
	}

	protected void buildTable() {
		
		check = new CheckInput();
		check.settList(list);
		if(!check.checkTxtValue()){
	        Notification.show("错误信息:", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}
		else{
			 pm = Integer.valueOf(plane.getText().getValue());
			 pn = Integer.valueOf(points.getText().getValue());
			if(pm<1){
		        Notification.show("错误信息:", "平面数必须大于等于1！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else if(pm>10){
		        Notification.show("错误信息:", "平面数必须小于等于10！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else if(pn>15){
		        Notification.show("错误信息:", "振动测点数必须小于或等于15！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else if(pn<2){
		        Notification.show("错误信息:", "振动测点数必须大于或等于2！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else if(pn<pm){
				  Notification.show("错误信息:", "振动测点数必须大于或等于平面数！", 
			        		Notification.TYPE_ERROR_MESSAGE);
			}
			else{
				multiTable= new MultiDataTable(WIDTH,NRO, pn, pm);
				CssLayout tablelayout = new CssLayout(multiTable);
				input.setContent(tablelayout);
		        multiCoeffCalc.addComponent(input);
		        multiCoeffCalc.addComponent(buildButton());
		        //setCompositionRoot(multiCoeffCalc);
			}
		}

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
			    

		HorizontalLayout button = new HorizontalLayout(reset,calc);
		button.setWidth("100%");
		button.setExpandRatio(reset, 1);
		resultPanel.addComponent(button);
		resultPanel.setComponentAlignment(button,  Alignment.MIDDLE_CENTER);
		resultPanel.setSpacing(false);
        return resultPanel;
	}
	
	protected void calc() {
		
		if(!multiTable.validateTable()){
	        Notification.show("错误信息:", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}
		else{
			vecA0 =new VectorBal[pn];
			vecA0 = multiTable.getTableValueA0();
			vecA1 = new VectorBal[pm][pn];
			vecA1 = multiTable.getTableValueA1();
			vecT =new VectorBal[pm];
			vecT = multiTable.getTableValueT();
			af= new VectorBal[pn][pm];
			checkBox =new boolean[pm];
			checkBox= multiTable.getCheckBox();
			x =new VectorBal[pm];
			for(int j=0; j<pn; j++){
				af[j][0]= vecA1[0][j].sub(vecA0[j]).div(vecT[0]);
			}
			for(int i=1; i<pm; i++){
				for(int j=0; j<pn; j++){
					if(checkBox[i-1]){
						af[j][i]= vecA1[i][j].sub(vecA1[i-1][j]).div(vecT[i]);
					}
					else{
						af[j][i]= vecA1[i][j].sub(vecA0[j]).div(vecT[i]);
					}
					
				}
			}
			for(int j=0; j<pn; j++){
				for(int i=0; i<pm; i++){
					//af[j][i].print();
				}
			}
			
			VectorBal temp=new VectorBal(0,0);
			vecAR= new VectorBal[pn];
			for(int j=0; j<pn; j++){
				vecAR[j]=temp.sub(vecA0[j]);
			}
			
			if(pm==pn){
				   Guass gu = new Guass(af, vecAR, pn);
		           x=gu.GuassCal(); 
				   for(int i=0; i<pm; i++){
						if(checkBox[i]){
							x[i]=x[i].sub(vecT[i]);
						}
				   }

               
			}
			else if(pn>pm){
		        LeastSquares ls= new LeastSquares(af,vecA0,pn,pm);
		        x= ls.LeastSquaresCal();
				for(int i=0; i<pm; i++){
					if(checkBox[i]){
						x[i]=x[i].sub(vecT[i]);
					}
					//x[i].print();
				}
			}
			multiResultTable= new MultiResultTable(WIDTH_RESULT, RO, pm);
			multiResultTable.setTableValue(vecT, x);
			CssLayout tablelayout = new CssLayout(multiResultTable);
			result.setContent(tablelayout);
	        multiCoeffCalc.addComponent(result);
		}
	}

	protected void clear() {//clear all data;
		multiTable.clearMultiTable();
	}
}
