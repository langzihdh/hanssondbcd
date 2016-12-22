package com.hansson.dybalcalc.balcalc.testcalc;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.calc.DoublePlaneBal;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestDouble {

	public static void main(String[] args) {  
	ArrayList <VectorBal> alist = new ArrayList<VectorBal>();
    VectorBal A0 = new VectorBal(7.05,166);  
    VectorBal B0 = new VectorBal(5.1,344);
    VectorBal A1 = new VectorBal(5.77,126);
    VectorBal B1 = new VectorBal(6.14,354);  
    VectorBal T1 = new VectorBal(20,345);
    VectorBal A2 = new VectorBal(9.11,176);
    VectorBal B2 = new VectorBal(4.44,271);
    VectorBal T2 = new VectorBal(20,161); 
    alist.add(A0);
    alist.add(B0);
    alist.add(T1);
    alist.add(A1);
    alist.add(B1);
    alist.add(T2);
    alist.add(A2);
    alist.add(B2);
    boolean ta=true;
    boolean tb=false;

    DoublePlaneBal dpb= new DoublePlaneBal(alist,ta,tb);
    ArrayList <VectorBal> temp=dpb.doublePlaneCal();
         for(int i=0;i<temp.size();i++){ 
    	     temp.get(i).print();
         } 
	 }
}
