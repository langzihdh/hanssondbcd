package com.hansson.dybalcalc.view.tools;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.TrialWeight;
import com.hansson.dybalcalc.component.TextCom;
import com.hansson.dybalcalc.event.CheckInput;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public final class  TrialWeightPanel extends CustomComponent{

    private TextCom w, n, a, r, s;
	private TextCom tw1, tw2, tw3, tw4, tw5;
	private ArrayList <TextCom> list = new ArrayList<TextCom>();
	private CheckInput check;
	private String value;
	
	public TrialWeightPanel() {
		 setCaption("动平衡试重计算");
		 setSizeFull();
		 HorizontalLayout fields = new HorizontalLayout();
		 fields.setSpacing(true);
		 fields.setSizeFull();
		 fields.addComponent(buildInput());
		 fields.addComponent(buildResult());
		 setCompositionRoot(fields);
	}

	private Component buildResult() {
		 Panel result = new Panel("计算结果");
		 result.addStyleName("mypanelexample");
		 result.setStyleName("v-panel-caption");
		 
		 tw1 = new TextCom("常用算法：", "(g)", "150px", true);
		 tw2 = new TextCom("申克算法：", "(g)", "150px", true);
		 tw3 = new TextCom("三菱算法：", "(g)", "150px", true);
		 tw4 = new TextCom("施维新算法：", "(g)", "150px", true);
		 tw5 = new TextCom("平均值：", "(g)", "150px", true);

		 final Button calc = new Button("计算");
		 calc.addStyleName(ValoTheme.BUTTON_PRIMARY);
		 calc.setIcon(FontAwesome.CALCULATOR);
		 calc.addClickListener(new ClickListener() {
		      @Override
		      public void buttonClick(final ClickEvent event) {
		           calc();
		      }
		 });
		 VerticalLayout resultform = new VerticalLayout(tw1,tw2,tw3,tw4,tw5,calc);
		 resultform.setComponentAlignment(calc, Alignment.MIDDLE_CENTER);
		 resultform.setSpacing(false);
		 resultform.setSizeFull();
		 result.setContent(resultform);
		 return result;
	}

	private Component buildInput() {
		 Panel input = new Panel("数据输入");
		 input.addStyleName("mypanelexample");
		 input.setStyleName("v-panel-caption");
		 input.setIcon(FontAwesome.EDIT);
		 
		 w = new TextCom("转子重量：", "(kg)", "150px", false);
		 n = new TextCom("平衡转速：", "(rpm)", "150px", false);
		 a = new TextCom("振动幅值：", "(μm)", "150px", false);
		 r = new TextCom("加重半径:", "(mm)", "150px", false);
		 s = new TextCom("灵敏度系数：", "", "150px", false);
		 list.add(w);
		 list.add(n);
		 list.add(a);
		 list.add(r);
		 list.add(s);
		 
	     final Button reset = new Button("重置");
	     reset.setIcon(FontAwesome.ERASER);
	     reset.addClickListener(new ClickListener() {
	            @Override
	            public void buttonClick(final ClickEvent event) {
	            	clear();
	            }
	     });
	     VerticalLayout inputform = new VerticalLayout(w,n,a,r,s,reset);
	     inputform.setComponentAlignment(reset, Alignment.MIDDLE_CENTER);
	     inputform.setSpacing(false);
	     inputform.setSizeFull();
	     input.setContent(inputform);
		 check = new CheckInput();
		 check.settList(list);
		 return input;
	}

	protected void calc() {
		double vw, vn, va, vr, vs;
		if(!check.checkTxtValue()){
	        Notification.show("错误信息", "参数输入错误，参数为空或输入格式错误！", 
	        		Notification.TYPE_ERROR_MESSAGE);
		}else{
			vw = Double.valueOf(w.getText().getValue());
			vn = Double.valueOf(n.getText().getValue());
			va = Double.valueOf(a.getText().getValue());
			vr = Double.valueOf(r.getText().getValue());
			if(s.getText().isEmpty()){
				vs=0.0;
			}
			else{
				vs = Double.valueOf(s.getText().getValue());
			}
		    TrialWeight tw= new TrialWeight(vw,vn,va,vr,vs);
		    double[] TW = tw.TWCal();
		    value = new DecimalFormat("#.##").format(TW[0]);
			tw1.getText().setValueIgnoreReadOnly(value);
			value = new DecimalFormat("#.##").format(TW[1]);
			tw2.getText().setValueIgnoreReadOnly(value);
			value = new DecimalFormat("#.##").format(TW[2]);
			tw3.getText().setValueIgnoreReadOnly(value);
			value = new DecimalFormat("#.##").format(TW[3]);
			tw4.getText().setValueIgnoreReadOnly(value);
			value = new DecimalFormat("#.##").format(TW[4]);
			tw5.getText().setValueIgnoreReadOnly(value);

		}
	}

	protected void clear() {
		 w.clearIgnoreReadOnly();
		 n.clearIgnoreReadOnly();
		 a.clearIgnoreReadOnly();
		 r.clearIgnoreReadOnly();
		 s.clearIgnoreReadOnly();
		 tw1.clearIgnoreReadOnly();
		 tw2.clearIgnoreReadOnly();
		 tw3.clearIgnoreReadOnly();
		 tw4.clearIgnoreReadOnly();
		 tw5.clearIgnoreReadOnly();
	}
}
