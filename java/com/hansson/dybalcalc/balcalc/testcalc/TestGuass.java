package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.Guass;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestGuass {
	


	   public static void main(String[] args) { 
		   VectorBal x[];  
		   VectorBal A0 = new VectorBal(-104410,-89);  
		   VectorBal B0 = new VectorBal(-97528,7);
		   VectorBal T1 = new VectorBal(400,160);
		   VectorBal T2 = new VectorBal(400,340); 
		   VectorBal A1 = new VectorBal(25,345);
		   VectorBal B1 = new VectorBal(27,69);  
		   VectorBal a[][];
		   VectorBal b[];
		   VectorBal a11=new VectorBal(208504,0);
		   VectorBal a12=new VectorBal(208463,-172);
		   VectorBal a21=new VectorBal(208463,172);
		   VectorBal a22=new VectorBal(208240,0);
		   VectorBal u11;
		   VectorBal u12;

	       VectorBal inputA[][] = {{new VectorBal(208504,0),new VectorBal(208463,187.48)},
	    	                       {new VectorBal(208463,172),new VectorBal(282400,0)}};  
	       a = inputA;            
	       VectorBal inputB[] = {new VectorBal(-104410,270.8),new VectorBal(-97528,75.64)};  
	       b = inputB;  

		   int n=2;
		   Guass gu = new Guass(a, b, n);
           x=gu.GuassCal(); 
		   for(int i=0;i<n;i++){ 
			   x[i].print();
		   } 
	   }

}
