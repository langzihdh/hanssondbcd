package com.hansson.dybalcalc.component;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;
import com.hansson.dybalcalc.event.IsDouble;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class MultiDataTable extends Table{
	private Object[] objVec, obj0, vecAmp, vecPha;
	private int n, m;
	private MulitiTableText[] vecA0, vecT;
	private CheckBox checkBox[];
	private MulitiTableText[][] vecA1;
	private CheckBox checkBox0;
	private String ampA, phaA;
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public Object[] getVecAmp() {
		return vecAmp;
	}

	public void setVecAmp(Object[] vecAmp) {
		this.vecAmp = vecAmp;
	}

	public Object[] getVecPha() {
		return vecPha;
	}

	public void setVecPha(Object[] vecPha) {
		this.vecPha = vecPha;
	}
	
	public MultiDataTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MultiDataTable(String width, boolean tag, int n, int m){
		this.n=n;
		this.m=m;
		addContainerProperty("名称", MultiDataLabel.class, null);
		addContainerProperty("试加重量(g)", MulitiTableText.class, null);
		addContainerProperty("试重状态", CheckBox.class, null);
		for(int i=0; i<n; i++){
		    addContainerProperty("测点"+(i+1), MulitiTableText.class, null);
		 }
		
		//n=n+3;

	    vecA0 = new MulitiTableText[n];
	    vecA1 = new MulitiTableText[m][n];
	    vecT = new MulitiTableText[m];
	    checkBox = new CheckBox[m];
	    
	    
	    for(int i=0; i<m; i++){
	    	vecT[i]=new MulitiTableText(width, tag);
	    }
	    
	    for(int i=0; i<n; i++){
	    	vecA0[i]=new MulitiTableText(width, tag);
	    }
	    
	    for(int i=0; i<m; i++){
	    		checkBox[i]=new CheckBox("保留", false);
	    }
	    checkBox0=new CheckBox("", false);
	    checkBox0.setVisible(false);;

		obj0= new Object[n+3];
		obj0[0] = new MultiDataLabel(0);
		obj0[1] = new MulitiTableText(width, true);
		obj0[2] = checkBox0;
		for(int i=3; i<n+3; i++){
				obj0[i] = vecA0[i-3];
		}
		addItem(obj0,0);
		
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				vecA1[i][j] = new MulitiTableText(width, tag);
			}
		}
		
		objVec = new Object[n+3];
	    
	    for(int i=0; i<m; i++){
			Integer itemId = new Integer(i+1);
			objVec[0] = new MultiDataLabel(i+1);
			objVec[1] = vecT[i];
			objVec[2]= checkBox[i];
	    	for(int j=3; j<n+3; j++){
	    		objVec[j] = vecA1[i][j-3];
		    }
			addItem(objVec,itemId);
	    }
	    
	    for(int j=0; j<n; j++){
	    	setColumnAlignment("测点"+(j+1), Align.CENTER);
	    }
	 // Set the footers
	    setFooterVisible(true);
	    //setColumnFooter("平面"+(j+1), new ComboBox("各面试重是否保留"));
	    for(int j=0; j<m; j++){
	    	//setColumnFooter("平面"+(j+1), new ComboBox());
	    }
	    
	    setColumnAlignment("名称", Align.CENTER);
	    setColumnAlignment("试加重量(g)", Align.CENTER);
	    setColumnAlignment("试重状态", Align.CENTER);
	    //setFrozenColumnCount(0);
	    setPageLength(size());
	    setSizeUndefined();
	}
	
	public VectorBal[] getTableValueA0(){
		VectorBal[] vectorBal= new VectorBal[n];
		for(int i=3; i<n+3; i++){
			if(!vecA0[i-3].getAmp().isEmpty() && !vecA0[i-3].getPha().isEmpty()){
				vectorBal[i-3] = new VectorBal(Double.valueOf(vecA0[i-3].getAmp().getValue()),
	                     Double.valueOf(vecA0[i-3].getPha().getValue()));
			}

		}
		return vectorBal;
	}
	
	public VectorBal[] getTableValueT(){
		VectorBal[] vectorBal= new VectorBal[m];
		for(int i=0; i<m; i++){
			if(!vecT[i].getAmp().isEmpty() && !vecT[i].getPha().isEmpty()){
				vectorBal[i] = new VectorBal(Double.valueOf(vecT[i].getAmp().getValue()),
	                     Double.valueOf(vecT[i].getPha().getValue()));
			}

		}
		return vectorBal;
	}
	
	public VectorBal[][] getTableValueA1(){
		VectorBal[][] vectorBal= new VectorBal[m][n];
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(!vecA1[i][j].getAmp().isEmpty() && !vecA1[i][j].getPha().isEmpty()){
					vectorBal[i][j] = new VectorBal(Double.valueOf(vecA1[i][j].getAmp().getValue()),
		                     Double.valueOf(vecA1[i][j].getPha().getValue()));
				}
			}


		}
		return vectorBal;
	}
	
	public boolean[] getCheckBox(){
		boolean[] bool= new boolean[m];
		for(int i=0; i<m; i++){
			if(checkBox[i].getValue()){
				bool[i] = true;
			}
			else{
				bool[i] = false;
			}

		}
		return bool;
	}
    
    public void clearMultiTable() {//clear all data;
		for(int i=0; i<m; i++){
			vecT[i].getAmp().clear();
			checkBox[i].setValue(false);
			for(int j=0; j<n; j++){
				vecA1[i][j].getAmp().clear();
				vecA1[i][j].getPha().clear();
			}
		}
		for(int j=0; j<n; j++){
			vecA0[j].getAmp().clear();
			vecA0[j].getPha().clear();
		}
	}
    
    public boolean validateTable() {
		boolean tag = true;
		IsDouble dbAmp, dbPha;
		for(int j=0; j<n; j++){
			ampA = vecA0[j].getAmp().getValue();
			phaA = vecA0[j].getPha().getValue();
		    dbAmp = new IsDouble(ampA);
			dbPha = new IsDouble(phaA);
			if(!dbAmp.isDouble() ||!dbPha.isDouble()){
				tag = false;
				break;
			}
		}
		
		for(int j=0; j<m; j++){
			ampA = vecT[j].getAmp().getValue();
			phaA = vecT[j].getPha().getValue();
		    dbAmp = new IsDouble(ampA);
			dbPha = new IsDouble(phaA);
			if(!dbAmp.isDouble() ||!dbPha.isDouble()){
				tag = false;
				break;
			}
		}
		
		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				ampA = vecA1[i][j].getAmp().getValue();
				phaA = vecA1[i][j].getPha().getValue();
			    dbAmp = new IsDouble(ampA);
				dbPha = new IsDouble(phaA);
				if(!dbAmp.isDouble() ||!dbPha.isDouble()){
					tag = false;
					break;
				}
			}

		}

		return tag;
    }
}
