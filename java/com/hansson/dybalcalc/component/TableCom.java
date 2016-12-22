package com.hansson.dybalcalc.component;

import java.text.DecimalFormat;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TableCom extends Table{
	private TableText[] vecAmp, vecPha;
	private Label[] name;
	
	public TableText[] getVecAmp() {
		return vecAmp;
	}

	public void setVecAmp(TableText[] vecAmp) {
		this.vecAmp = vecAmp;
	}

	public TableText[] getVecPha() {
		return vecPha;
	}

	public void setVecPha(TableText[] vecPha) {
		this.vecPha = vecPha;
	}

	public TableCom(String str[], String width, boolean tag){
		addContainerProperty("", Label.class, null);
		addContainerProperty("振幅(μm)", TableText.class, null);
		addContainerProperty("相位(°)", TableText.class, null);
		
	    vecAmp = new TableText[str.length];
	    vecPha = new TableText[str.length];
	    name = new Label[str.length];
	    for(int i=0; i<str.length; i++){
		   vecAmp[i] = new TableText(width,tag);
		   vecPha[i] = new TableText(width,tag);
		   name[i] = new Label(str[i]);
		   Integer itemId = new Integer(i);
		   addItem(new Object[] {name[i] , vecAmp[i],vecPha[i]},itemId);
	    }
	    setColumnAlignment("", Align.RIGHT);
	    setColumnAlignment("振幅(μm)", Align.CENTER);
	    setColumnAlignment("相位(°)", Align.CENTER);
	    setPageLength(size());
	    setSizeUndefined();
	}
	
	public VectorBal[] getTableComValue(){
		VectorBal[] vectorBal= new VectorBal[name.length];
		for(int i=0; i<name.length; i++){
			if(!vecAmp[i].getText().isEmpty() && !vecPha[i].getText().isEmpty()){
				vectorBal[i] = new VectorBal(Double.valueOf(vecAmp[i].getText().getValue()),
	                                         Double.valueOf(vecPha[i].getText().getValue()));
			}

		}
		return vectorBal;
	}	 
	
    public void setTableComValue(VectorBal[] vec){
    	for(int i=0; i<name.length; i++){
		   String ampStr = new DecimalFormat("#.###").format(vec[i].getamp());
		   String phaStr = new DecimalFormat("#.###").format(vec[i].getpha());
		   vecAmp[i].getText().setValueIgnoreReadOnly(ampStr);
		   vecPha[i].getText().setValueIgnoreReadOnly(phaStr);
    	}
    }
    
    public void clearTableCom() {//clear all data;
		for(int i=0; i<name.length; i++){
			vecAmp[i].clearIgnoreReadOnly();
			vecPha[i].clearIgnoreReadOnly();
		}
	}
}
