package com.hansson.dybalcalc.component;

import org.vaadin.ui.NumberField;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class TableText extends CustomComponent{
	private NumberField text;
	
	public NumberField getText() {
		return text;
	}

	public void setText(NumberField textField) {
		this.text = textField;
	}

	public TableText(String width, boolean tag) {
		 text = new NumberField();
		 text.setImmediate(true);
		 text.setDecimalPrecision(4); 
		 text.setGroupingUsed(false);
		 text.setErrorText("非法数值输入！");
		 text.setWidth(width);
		 text.setReadOnly(tag);
		 text.setStyleName(ValoTheme.TEXTFIELD_SMALL);
		 setCompositionRoot(text);  
	}
	
    public void clearIgnoreReadOnly() {
        boolean wasReadOnly = text.isReadOnly();
        if (text.isReadOnly()) {
        	text.setReadOnly(false);
        }
        text.clear();
        if (wasReadOnly) {
        	text.setReadOnly(true);
        }
    }
}
