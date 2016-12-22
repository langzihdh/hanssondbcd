package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.LeastSquares;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestLeastSquaresCal {


	public TestLeastSquaresCal() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=3;
		int m=2;
		VectorBal a[][]=new VectorBal[n][m];
		VectorBal at[][]=new VectorBal[n][m];
		VectorBal b[]=new VectorBal[n];
		VectorBal aT[][]= new VectorBal[a[0].length][a.length];
		VectorBal aTa[][]=new VectorBal[m][n];
		VectorBal aTb[]=new VectorBal[n];
	    VectorBal inputA[][] = {{new VectorBal(0.252,144),new VectorBal(0.180,307)},
	    		                {new VectorBal(0.150,66),new VectorBal(0.300,215)},
                                {new VectorBal(0.350,162),new VectorBal(0.400,9)}};  
	    VectorBal inputC[][] = {{new VectorBal(0.252,216),new VectorBal(0.180,53)},
                {new VectorBal(150,294),new VectorBal(300,145)},
                {new VectorBal(350,198),new VectorBal(400,351)}};  
        a = inputA;   
        at=inputC;
        VectorBal inputB[] = {new VectorBal(100,68),new VectorBal(50,0),new VectorBal(210,66)};  
        b = inputB;  
        LeastSquares ls= new LeastSquares(a,b,n,m);
        VectorBal[] x=new VectorBal[a[0].length];
        		x = ls.LeastSquaresCal();
		   for(int i=0;i<a[0].length;i++){ 
			  x[i].print();
		   } 
	}
}
