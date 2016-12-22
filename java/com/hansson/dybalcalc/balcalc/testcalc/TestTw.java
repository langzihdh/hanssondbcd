package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.TrialWeight;

public class TestTw {

	public TestTw() {
		// TODO Auto-generated constructor stub
	}
	 public static void main(String[] args) {
	       double W, N, A, R, S;
	       double[] TW = new double[5];
	       
	       W=2000;
	       N=1500;
	       A=150;
	       R=800;
	       S=300;
	       
	       TrialWeight tw= new TrialWeight(W,N,A,R,S);
	       TW=tw.TWCal();
	       for(int i=0;i<5;i++){
	    	   System.out.println(TW[i]);   
	       }
	 }
}
