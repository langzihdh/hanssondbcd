package com.hansson.dybalcalc.component;

import org.vaadin.ui.NumberField;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class TextCom extends CustomComponent{
	private NumberField text;
	
	public NumberField getText() {
		return text;
	}

	public void setText(NumberField textField) {
		this.text = textField;
	}

	public TextCom(String txt, String unit, String width, boolean tag) {
		 text = new NumberField(txt);
		 text.setImmediate(true);
		 text.setDecimalPrecision(4); 
		 text.setGroupingUsed(false);
		 text.setErrorText("非法数值输入！");
		 text.setWidth(width);
		 text.setReadOnly(tag);
		 text.setStyleName(ValoTheme.TEXTFIELD_TINY);
		 Label unitLabel = new Label(unit);
		 HorizontalLayout layout = new HorizontalLayout(text,unitLabel);
		 layout.setSpacing(true);
		 layout.setComponentAlignment(unitLabel, Alignment.BOTTOM_RIGHT);
		 setCompositionRoot(layout);  
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
