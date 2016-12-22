package com.hansson.dybalcalc.component;

import java.text.DecimalFormat;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class MultiResultTable extends Table{
	private Object[] obj0, vec;
	private int  m;
	private MulitiTableText[] vecTrail, vecX;

	

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	public MulitiTableText[] getVecTrail() {
		return vecTrail;
	}

	public void setVecTrail(MulitiTableText[] vecTrail) {
		this.vecTrail = vecTrail;
	}

	public MulitiTableText[] getVecX() {
		return vecX;
	}

	public void setVecX(MulitiTableText[] vecX) {
		this.vecX = vecX;
	}
	
	public MultiResultTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultiResultTable(String width, boolean tag, int m ){
		this.m=m;
		addContainerProperty("名称", MultiResultLabel.class, null);
		for(int i=0; i<m; i++){
		    addContainerProperty("平面"+(i+1), MulitiTableText.class, null);
		}
		
	    vecTrail = new MulitiTableText[m];
	    vecX = new MulitiTableText[m];
	    
	    for(int i=0; i<m; i++){
	    	vecTrail[i]=new MulitiTableText(width, tag);
	    }
	    
	    for(int i=0; i<m; i++){
	    	vecX[i]=new MulitiTableText(width, tag);
	    }

		obj0= new Object[m+1];
		obj0[0] = new MultiResultLabel("试加重量(g)","试重角度(°)");
		for(int i=1; i<m+1; i++){
			obj0[i] = vecTrail[i-1];
		}
		addItem(obj0,0);		
		
	    vec = new Object[m+1];
	    vec[0] = new MultiResultLabel("校正重量(g)","校重角度(°)");
	    for(int i=1; i<m+1; i++){
	 		vec[i] = vecX[i-1];
	    }
	    addItem(vec,1);
	    
	    for(int j=0; j<m; j++){
	    	setColumnAlignment("平面"+(j+1), Align.CENTER);
	    }
	    
	    setColumnAlignment("名称", Align.CENTER);
	    setPageLength(size());
	    setSizeUndefined();
	}
	
	public  void setTableValue(VectorBal[] vectorT, VectorBal[] vectorX ){
		for(int i=0; i<m; i++){
			String ampT = new DecimalFormat("#.##").format(vectorT[i].getamp());
	        String phaT = new DecimalFormat("#.##").format(vectorT[i].getpha());
			vecTrail[i].getAmp().setValueIgnoreReadOnly(ampT);
			vecTrail[i].getPha().setValueIgnoreReadOnly(phaT);
			
			String ampX = new DecimalFormat("#.##").format(vectorX[i].getamp());
	        String phaX = new DecimalFormat("#.##").format(vectorX[i].getpha());
	        vecX[i].getAmp().setValueIgnoreReadOnly(ampX);
	        vecX[i].getPha().setValueIgnoreReadOnly(phaX);
		}
	}
}
