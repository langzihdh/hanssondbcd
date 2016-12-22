package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.MultiPlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestMultiPlaneBal {

	public TestMultiPlaneBal() {
		// TODO Auto-generated constructor stub
	}


	   public static void main(String[] args) { 
		  
	       VectorBal inputA[][] = {{new VectorBal(81,108),new VectorBal(74,290)},
	    		   {new VectorBal(41,20),new VectorBal(118,137)}};  
	       VectorBal a[][]  = inputA;  
	         
	       VectorBal inputB[] = {new VectorBal(-30,340),new VectorBal(-100,20)};  
	       VectorBal b[] = inputB;  
	       int n=2;
		   int m=2;
		   MultiPlaneBal mpc = new MultiPlaneBal(a,b,n,m);
		   VectorBal x[]=mpc.MultiPlaneCal();

		   if(n==m){
			 for(int i=0;i<n;i++){ 
			     x[i].print();
		     } 
		   }
		   else if(n>m){
			 for(int i=0;i<a[0].length;i++){ 
				 x[i].print();
			 } 
		   }
			   
	   }
}
