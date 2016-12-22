package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.calc.VecDecompose;
import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class TestVecDecompose {

	public TestVecDecompose() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   VectorBal v1 = new VectorBal(49,314);  
		   VectorBal v2 = new VectorBal(41,97);
		   VecDecompose vd1 = new VecDecompose(v1, v2);
		   VectorBal vz =vd1.vecVZCal();
		   VectorBal vf =vd1.vecVFCal();
		   
		   vz.print();
		   vf.print();
		   
	}

}
