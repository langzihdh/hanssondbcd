package com.hansson.dybalcalc.event;

import java.util.ArrayList;

import com.hansson.dybalcalc.component.TextCom;
import com.hansson.dybalcalc.component.VectorCom;

public class CheckInput {

	private ArrayList <VectorCom> vList = new ArrayList<VectorCom>();
	private ArrayList <TextCom> tList = new ArrayList<TextCom>();


	
	public ArrayList<VectorCom> getvList() {
		return vList;
	}

	public void setvList(ArrayList<VectorCom> vList) {
		this.vList = vList;
	}

	public ArrayList<TextCom> gettList() {
		return tList;
	}

	public void settList(ArrayList<TextCom> tList) {
		this.tList = tList;
	}

	public boolean checkVecValue(){
		String amp, pha;
		boolean tag = true;
		for(int i=0; i<vList.size(); i++){
			amp = vList.get(i).getAmp().getValue();
			pha = vList.get(i).getPha().getValue();
			if(!isDouble(amp) || !isDouble(pha)){
				tag = false;
				break;
			}
		}
		return tag;
		
	}
	
	public boolean checkTxtValue(){
		boolean tag = true;
		for(int i=0; i<tList.size(); i++){
			String value = tList.get(i).getText().getValue();
			if(!isDouble(value)){
				tag = false;
				
				break;
			}
		}
		return tag;
	}
	
	public boolean isDouble(String value){
		if(value==null){
			return false;
		}
		else if(value.matches("-?[0-9]+.*[0-9]*")) {
			return true;
		} 
		else {
			return false;
		}
	}
}
