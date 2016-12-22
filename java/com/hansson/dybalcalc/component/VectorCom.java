package com.hansson.dybalcalc.component;

import java.text.DecimalFormat;

import org.vaadin.ui.NumberField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class VectorCom extends CustomComponent{

	private NumberField amp;
	private NumberField pha;
	
    public NumberField getAmp() {
		return amp;
	}

	public void setAmp(NumberField amp) {
		this.amp = amp;
	}

	public NumberField getPha() {
		return pha;
	}

	public void setPha(NumberField pha) {
		this.pha = pha;
	}

	public VectorCom(String label, String width, boolean tag) {
		Label aLabel = new Label(label);
		amp = new NumberField("振幅");
		amp.setWidth(width);
		amp.setStyleName(ValoTheme.TEXTFIELD_TINY);
		amp.setImmediate(true);
		amp.setDecimalPrecision(4); 
		amp.setGroupingUsed(false);
		amp.setErrorText("非法数值输入！");
		amp.setReadOnly(tag);
		
		Label apLabel = new Label("μm  ∠");
		pha = new NumberField("相位");
		pha.setWidth(width);
		pha.setStyleName(ValoTheme.TEXTFIELD_TINY);
		pha.setImmediate(true);
		pha.setDecimalPrecision(4); 
		pha.setGroupingUsed(false);
		pha.setErrorText("非法数值输入！");
		pha.setReadOnly(tag);
		Label pLabel = new Label("°");
		pLabel.setSizeUndefined();
	    HorizontalLayout vector = new HorizontalLayout(aLabel,amp,apLabel,pha,pLabel);
	    vector.setSpacing(true);
	    vector.setComponentAlignment(aLabel, Alignment.BOTTOM_RIGHT);
	    vector.setComponentAlignment(apLabel, Alignment.BOTTOM_RIGHT);
	    vector.setComponentAlignment(pLabel, Alignment.MIDDLE_CENTER);
	    vector.setSizeUndefined();
        setCompositionRoot(vector);   
    } 
	
    public void clearIgnoreReadOnly() {
        boolean wasReadOnly = amp.isReadOnly();
        if (amp.isReadOnly()) {
        	amp.setReadOnly(false);
        	pha.setReadOnly(false);
        }
		amp.clear();
		pha.clear();
        if (wasReadOnly) {
        	amp.setReadOnly(true);
        	pha.setReadOnly(true);
        }
    }
    
    public void setValue(){
		String ampStr = new DecimalFormat("#.####").format(amp.getValue());
		String phaStr = new DecimalFormat("#.####").format(pha.getValue());
		amp.setValueIgnoreReadOnly(ampStr);
		pha.setValueIgnoreReadOnly(phaStr);
    }
}
