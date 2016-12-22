package com.hansson.dybalcalc.balcalc.calc;

public class AllowUnbalCal {
	public double m, n, r, g, eper, uper;
	
	public AllowUnbalCal(double m, double n, double r, double g) {
		super();
		this.m = m;
		this.n = n;
		this.r = r;
		this.g = g;
	}

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getEper() {
		return eper;
	}

	public void setEper(double eper) {
		this.eper = eper;
	}

	public double getUper() {
		return uper;
	}

	public void setUper(double uper) {
		this.uper = uper;
	}
	
	public void calcEper(){
		eper = g*60*1000/(2*Math.PI*n*r);
	}
	
	public void calcUper(){
		uper = eper*m;
	}
}
