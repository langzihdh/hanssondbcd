package com.hansson.dybalcalc.balcalc.calc;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class MultiPlaneBal {
	private VectorBal a[][];  
	private VectorBal b[];  
	private VectorBal x[];
	private int m; 
	private int n;  

	public MultiPlaneBal() {
		// TODO Auto-generated constructor stub
	}
	public MultiPlaneBal(VectorBal[][] a, VectorBal[] b, int n, int m) {
		this.a=a;
		this.b=b;
		this.n=n;
		this.m=m;
	}
	  
    public  VectorBal[][] getA() {
		return a;
	}
	public  void setA(VectorBal[][] a) {
		this.a = a;
	}
	public  VectorBal[] getB() {
		return b;
	}
	public  void setB(VectorBal[] b) {
		this.b = b;
	}
	public  VectorBal[] getX() {
		return x;
	}
	public  void setX(VectorBal[] x) {
		this.x = x;
	}

	public  VectorBal[] MultiPlaneCal() {
		if(n==m){//��������ڼ���ƽ���������ø�˹�㷨�����ط��̽⣻
			Guass gs= new Guass(a,b,n);
			x=gs.GuassCal();
		}
		else if(n>m){//��������ڼ���ƽ������������С���˷������ط��̽⣻
			LeastSquares ls= new LeastSquares(a,b,n,m);
			x=ls.LeastSquaresCal();
		}
		return x;
	}
}
