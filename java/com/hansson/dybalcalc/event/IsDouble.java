package com.hansson.dybalcalc.event;

public class IsDouble {
	String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public IsDouble() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IsDouble(String text) {
		super();
		this.text = text;
	}

	public boolean isDouble(){
		//text= text.trim();
		if(text==null){
			return false;
		}
		else if(text.matches("-?[0-9]+.*[0-9]*")) {
			return true;
		} 
		else {
			return false;
		}
	}
}
