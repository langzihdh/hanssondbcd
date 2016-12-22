package com.hansson.dybalcalc.event;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.component.TableText;

public class SetTextValue {
	private TableText[] ampText, phaText;
	private ArrayList <VectorBal> list;
	private String amp;
	private String pha;

	
	
	public SetTextValue(TableText[] ampText, TableText[] phaText, ArrayList<VectorBal> list) {
		super();
		this.ampText = ampText;
		this.phaText = phaText;
		this.list = list;
	}


	public TableText[] getAmpText() {
		return ampText;
	}


	public void setAmpText(TableText[] ampText) {
		this.ampText = ampText;
	}


	public TableText[] getPhaText() {
		return phaText;
	}


	public void setPhaText(TableText[] phaText) {
		this.phaText = phaText;
	}


	public ArrayList<VectorBal> getList() {
		return list;
	}

	public void setList(ArrayList<VectorBal> list) {
		this.list = list;
	}

	public void setValue(){
		for(int i=0; i<list.size(); i++){
	        amp = new DecimalFormat("#.##").format(list.get(i).getamp());
	        ampText[i].getText().setValueIgnoreReadOnly(amp);

	        pha = new DecimalFormat("#.##").format(list.get(i).getpha());
	        phaText[i].getText().setValueIgnoreReadOnly(pha);
		}
		
	}

}
