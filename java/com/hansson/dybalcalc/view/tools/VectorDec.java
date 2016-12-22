package com.hansson.dybalcalc.view.tools;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.VecDecompose;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.VectorCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.hansson.dybalcalc.event.SetValue;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Responsive;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class VectorDec extends CustomComponent {

    private VectorCom veca, vecb, vecz, vecf;
    private ArrayList <VectorCom> list = new ArrayList<VectorCom>();
	private SetValue setValue;

	public VectorDec() {
        setSizeFull();
        setCaption("矢量正反对称分解");
        VerticalLayout vectorCalc = new VerticalLayout();
        Panel input = new Panel("数据输入");
        input.setSizeFull();
        input.setStyleName("v-panel-caption");
        input.setIcon(FontAwesome.EDIT);
        
        Panel result = new Panel("计算结果");
        result.setSizeFull();
        result.setStyleName("v-panel-caption");
        
        Component inputForm = buildInput();
        input.setContent(inputForm);
        vectorCalc.addComponent(input);
        Component resultForm = buildResult();
        result.setContent(resultForm);
        vectorCalc.addComponent(result);
        setCompositionRoot(vectorCalc);
    }
    
	private Component buildResult() {
		final VerticalLayout resultPanel = new VerticalLayout();
		resultPanel.setSpacing(true);
	    vecz = new VectorCom("正对称分量Z:", "130px", true);
        vecf = new VectorCom("反对称分量F:", "130px", true);

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
		resultPanel.addComponent(vecz);
		resultPanel.addComponent(vecf);
		resultPanel.addComponent(button);
		button.setExpandRatio(reset, 1);
        Responsive.makeResponsive(resultPanel);

        return resultPanel;
	}

	private Component buildInput() {
        final VerticalLayout inputPanel = new VerticalLayout();
        inputPanel.setSpacing(true);
        
        veca = new VectorCom("矢量A:", "150px", false);
        vecb = new VectorCom("矢量B:", "150px", false);
        list.add(veca);
        list.add(vecb);
	    inputPanel.addComponent(veca);
	    inputPanel.addComponent(vecb);
        Responsive.makeResponsive(inputPanel);

        return inputPanel;
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
			VecDecompose vd1 = new VecDecompose(v1, v2);
			VectorBal vz =vd1.vecVZCal();
			VectorBal vf =vd1.vecVFCal();

			setValue = new SetValue(vecz, vz);
			setValue.setValue();

			setValue = new SetValue(vecf, vf);
			setValue.setValue();
		}
	}

	protected void clear() {//clear all data;
		veca.clearIgnoreReadOnly();
		vecb.clearIgnoreReadOnly();
		vecz.clearIgnoreReadOnly();
		vecf.clearIgnoreReadOnly();

	}
}
