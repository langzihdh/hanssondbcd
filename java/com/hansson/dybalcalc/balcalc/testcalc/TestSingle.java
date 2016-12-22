package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.SinglePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestSingle {


	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  

        VectorBal vectorA0 = new VectorBal(90,40);  
        VectorBal vectorA1 = new VectorBal(50,140);
        VectorBal vectorT = new VectorBal(400,290);
		SinglePlaneBal temp= new SinglePlaneBal(vectorA0,vectorA1,vectorT);
		temp.SinglePlaneCal();
 
		for(int i=0; i<temp.getList().size(); i++){
			temp.getList().get(i).print();
		}

   
    }  
}
