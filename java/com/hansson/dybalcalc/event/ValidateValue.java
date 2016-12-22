package com.hansson.dybalcalc.event;

import com.hansson.dybalcalc.component.TableCom;
import com.hansson.dybalcalc.component.TableText;

public class ValidateValue {
	private TableCom tableCom;
	private String[] str;

	
	public TableCom getTableCom() {
		return tableCom;
	}

	public void setTableCom(TableCom tableCom) {
		this.tableCom = tableCom;
	}

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}

	public ValidateValue(TableCom tableCom, String[] str) {
		super();
		this.tableCom = tableCom;
		this.str = str;
	}

	public boolean validateValue() {
		boolean tag = true;
		String valueAmp,valuepha;
		IsDouble dbAmp, dbPha;
		TableText[] ampA=tableCom.getVecAmp();
		TableText[] phaA=tableCom.getVecPha();
		for(int i=0; i<str.length; i++){
		    valueAmp=ampA[i].getText().getValue();
			valuepha=phaA[i].getText().getValue();
			dbAmp = new IsDouble(valueAmp);
			dbPha = new IsDouble(valuepha);
			if(!dbAmp.isDouble() ||!dbPha.isDouble()){
				tag = false;
				break;
			}
		}
		return tag;
	}
}
