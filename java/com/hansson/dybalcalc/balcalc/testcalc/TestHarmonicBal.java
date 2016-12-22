package com.hansson.dybalcalc.balcalc.testcalc;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.Harmonic_EffBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestHarmonicBal {

	 public static void main(String[] args) {  
	 ArrayList <VectorBal> alist = new ArrayList<VectorBal>(8);
     VectorBal A0 = new VectorBal(49,314);  
     VectorBal B0 = new VectorBal(41,97);
     VectorBal T1 = new VectorBal(400,160);
     VectorBal T2 = new VectorBal(400,340); 
     VectorBal A1 = new VectorBal(25,345);
     VectorBal B1 = new VectorBal(27,69);  

     alist.add(A0);
     alist.add(B0);
     alist.add(T1);
     alist.add(T2);
     alist.add(A1);
     alist.add(B1);

     boolean t=false;

     Harmonic_EffBal dpb= new Harmonic_EffBal(alist,t);
     dpb.harmonicCal();
          for(int i=0;i<12;i++){ 
        	  dpb.getHar().get(i).print();; 
          } 
	 }
}
