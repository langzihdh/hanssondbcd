package com.hansson.dybalcalc.event;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.VectorCom;

public class SetValue {
	private VectorCom textField;
	private VectorBal vectorBal;
	private String amp;
	private String pha;
	private BigDecimal bigDec;
	
	public VectorCom getTextField() {
		return textField;
	}

	public void setTextField(VectorCom textField) {
		this.textField = textField;
	}

	public VectorBal getVectorBal() {
		return vectorBal;
	}

	public void setVectorBal(VectorBal vectorBal) {
		this.vectorBal = vectorBal;
	}

	public SetValue(VectorCom textField,VectorBal vectorBal){
		this.textField = textField;
		this.vectorBal = vectorBal;
	}
	
	public void setValue(){
		amp = new DecimalFormat("#.####").format(vectorBal.getamp());
        pha = new DecimalFormat("#.####").format(vectorBal.getpha());
        textField.getAmp().setValueIgnoreReadOnly(amp);
        textField.getPha().setValueIgnoreReadOnly(pha);
	}

}
