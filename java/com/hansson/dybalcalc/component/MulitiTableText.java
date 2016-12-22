package com.hansson.dybalcalc.component;

import org.vaadin.ui.NumberField;

import com.hansson.dybalcalc.event.IsDouble;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MulitiTableText extends CustomComponent{
	private NumberField amp, pha;
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

	private VerticalLayout root;

	public MulitiTableText(String width, boolean tag) {
		 root = new VerticalLayout();
		 amp = new NumberField();
		 amp.setImmediate(true);
		 amp.setDecimalPrecision(4); 
		 amp.setGroupingUsed(false);
		 amp.setErrorText("非法数值输入！");
		 amp.setWidth(width);
		 amp.setReadOnly(tag);
		 amp.setStyleName(ValoTheme.TEXTFIELD_SMALL);
		 
		 pha = new NumberField();
		 pha.setImmediate(true);
		 pha.setDecimalPrecision(4); 
		 pha.setGroupingUsed(false);
		 pha.setErrorText("非法数值输入！");
		 pha.setWidth(width);
		 pha.setReadOnly(tag);
		 pha.setStyleName(ValoTheme.TEXTFIELD_SMALL);
		 
		 root.addComponent(amp);
		 root.addComponent(pha);
		 root.setSpacing(false);
		 setCompositionRoot(root);  
	}
	
	public boolean validateValue() {
		boolean tag = true;
		IsDouble dbAmp, dbPha;
		String ampA = amp.getValue();
		String phaA = pha.getValue();
	    dbAmp = new IsDouble(ampA);
		dbPha = new IsDouble(phaA);
		if(!dbAmp.isDouble() ||!dbPha.isDouble()){
			tag = false;
		}
		return tag;
	}
}
