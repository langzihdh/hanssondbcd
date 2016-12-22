package com.hansson.dybalcalc.balcalc.calc;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class SinglePlaneBal {
	private VectorBal vecA0, vecA1, vecT;
	private ArrayList <VectorBal> list = new ArrayList<VectorBal>();
	
    public SinglePlaneBal(VectorBal vecA0,VectorBal vecT,VectorBal vecA1){ // �������Ĺ��췽��  
        this.vecA0 = vecA0;  
        this.vecA1 = vecA1; 
        this.vecT = vecT;  
    }  
  
    public SinglePlaneBal() {  
        // TODO Auto-generated constructor stub  
    }  

    
    public ArrayList<VectorBal> getList() {
		return list;
	}

	public void setList(ArrayList<VectorBal> list) {
		this.list = list;
	}

	public void SinglePlaneCal() {

    	 VectorBal temp=new VectorBal(0,0);
    	 VectorBal vecEf = (vecA1.sub(vecA0)).div(vecT);
    	 list.add(vecEf);
    	 list.add((temp.sub(vecA0)).div(vecEf));
    	 list.add((temp.sub(vecA1)).div(vecEf));

    } 

    
}
