package com.hansson.dybalcalc.balcalc.calc;

public class TrialWeight {
    public double W, N, A, R, S;
    double[] TW = new double[5];
	public double getW() {
		return W;
	}
	public void setW(double w) {
		W = w;
	}
	public double getN() {
		return N;
	}
	public void setN(double n) {
		N = n;
	}
	public double getA() {
		return A;
	}
	public void setA(double a) {
		A = a;
	}
	public double getR() {
		return R;
	}
	public void setR(double r) {
		R = r;
	}
	public double getS() {
		return S;
	}
	public void setS(double s) {
		S = s;
	}
	public double[] getTW() {
		return TW;
	}
	public void setTW(double[] tW) {
		TW = tW;
	}
	public TrialWeight(double w, double n, double a, double r, double s) {
		W = w;
		N = n;
		A = a;
		R = r;
		S = s;
	}  
	
	public double[] TWCal(){
		TW[0]=W*A/(15*R*Math.pow(N/3000, 2));
		TW[1]=10000*W/(R*Math.pow(N/100, 2));
		TW[2]=W*A/(2*R);
		TW[3]=Math.pow(10, 6)*W*A*9.8/Math.pow(6.28*N/60, 2)/S/R;
		TW[4]=(TW[0]+TW[1]+TW[2]+TW[3])/4;
		return TW;
		
	}
}
