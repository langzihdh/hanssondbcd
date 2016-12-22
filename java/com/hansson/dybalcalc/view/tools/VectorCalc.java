package com.hansson.dybalcalc.view.tools;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.VectorCom;
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
public class VectorCalc extends CustomComponent {

    private VectorCom veca, vecb, vecc;
	private ComboBox comboBox;
	private ArrayList <VectorCom> list = new ArrayList<VectorCom>();
	private VectorBal vc;
	private int tag;
	private SetValue setValue;
	private NativeSelect nativeSelect;

	public VectorCalc() {
        setSizeFull();
        setCaption("矢量运算");
        VerticalLayout vectorDec = new VerticalLayout();
        Panel input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setIcon(FontAwesome.EDIT);
        
        Panel result = new Panel("计算结果");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
        
        Component inputForm = buildInput();
        input.setContent(inputForm);
        vectorDec.addComponent(input);
        Component resultForm = buildResult();
        result.setContent(resultForm);
        vectorDec.addComponent(result);
        setCompositionRoot(vectorDec);
    }
    
	private Component buildInput() {
        final VerticalLayout inputPanel = new VerticalLayout();
        inputPanel.setSpacing(true);
        
        veca = new VectorCom("矢量A:", "150px", false);
        vecb = new VectorCom("矢量B:", "150px", false);
        list.add(veca);
        list.add(vecb);
        
        nativeSelect = new NativeSelect("矢量符号选择");
        nativeSelect.addItem("A＋B");
        nativeSelect.addItem("A－B");
        nativeSelect.addItem("A×B");
        nativeSelect.addItem("A÷B");
        nativeSelect.isReadOnly();
        //nativeSelect.setItemCaption(i, "Option " + i);
        nativeSelect.setNullSelectionAllowed(false);
        nativeSelect.setValue("A＋B");
        nativeSelect.setImmediate(true);
		
		inputPanel.addComponent(veca);
		inputPanel.addComponent(vecb);
		inputPanel.addComponent(nativeSelect);
		inputPanel.setComponentAlignment(nativeSelect, Alignment.MIDDLE_CENTER);
        Responsive.makeResponsive(inputPanel);

        return inputPanel;
    }
	
	private Component buildResult() {
        final VerticalLayout resultPanel = new VerticalLayout();
        resultPanel.setSpacing(true);
        vecc = new VectorCom("矢量C:", "150px", true);

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

		resultPanel.addComponent(vecc);
		resultPanel.addComponent(button);
		button.setExpandRatio(reset, 1);
        Responsive.makeResponsive(resultPanel);

        return resultPanel;
    }
	
	protected void calc() {
		CheckInput check = new CheckInput();
		check.setvList(list);
		if(!check.checkVecValue()){
	        Notification.show("错误信息", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}else{
			Double vAa = Double.valueOf(veca.getAmp().getValue());
			Double vPa = Double.valueOf(veca.getPha().getValue());
			Double vAb = Double.valueOf(vecb.getAmp().getValue());
			Double vPb = Double.valueOf(vecb.getPha().getValue());
			VectorBal v1 = new VectorBal(vAa,vPa);  
			VectorBal v2 = new VectorBal(vAb,vPb);
			

			if(nativeSelect.isEmpty()){
		        Notification.show("错误信息", "请选择运算符号！", 
		        		Notification.TYPE_ERROR_MESSAGE);
			}
			else{
				
				switch (boxValue()) {
				
	            case 1:
	    			vc =v1.add(v2);
	    			setValue = new SetValue(vecc, vc);
	    			setValue.setValue();
	                break;
	            case 2:
	    			vc =v1.sub(v2);
	    			setValue = new SetValue(vecc, vc);
	    			setValue.setValue();
	                break;
	            case 3:
	    			vc =v1.mul(v2);
	    			setValue = new SetValue(vecc, vc);
	    			setValue.setValue();
	                break;
	            case 4:
	    			vc =v1.div(v2);
	    			setValue = new SetValue(vecc, vc);
	    			setValue.setValue();
	                break;
	            case 0:
	                break;
	            }
			}

		}
	}

	private int boxValue() {
		String str = (String) nativeSelect.getValue();
		
		if(str=="A＋B"){
			tag=1;
		}
		else if(str=="A－B"){
			tag=2;
		}
		else if(str=="A×B"){
			tag=3;
		}
		else if (str=="A÷B"){
			tag=4;
		}
		else{
			tag=0;
		}
		return tag;
	}

	protected void clear() {//clear all data;
		veca.clearIgnoreReadOnly();
		vecb.clearIgnoreReadOnly();
		vecc.clearIgnoreReadOnly();
	}
}
