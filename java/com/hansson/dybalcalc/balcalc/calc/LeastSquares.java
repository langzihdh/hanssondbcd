package com.hansson.dybalcalc.balcalc.calc;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class LeastSquares {

	private VectorBal a[][],b[],x[],at[][];  
	private int n,m; 

	
	public LeastSquares() {
		// TODO Auto-generated constructor stub
	}
	public LeastSquares(VectorBal[][] a, VectorBal[] b, int n, int m) {
		this.a=a;
		this.b=b;
		this.n=n;
		this.m=m;
	}
	public VectorBal[][] getA() {
		return a;
	}
	public void setA(VectorBal[][] a) {
		this.a = a;
	}
	public VectorBal[] getB() {
		return b;
	}
	public void setB(VectorBal[] b) {
		this.b = b;
	}
	public VectorBal[] getX() {
		return x;
	}
	public void setX(VectorBal[] x) {
		this.x = x;
	}
	public VectorBal[] LeastSquaresCal(){//��С���˷�

		VectorBal aT[][]=new VectorBal[m][n];
		VectorBal aTa[][]=new VectorBal[m][n];
		VectorBal aTb[]=new VectorBal[n];
		at=new VectorBal[n][m];
		x=new VectorBal[m];
		
		for(int i=0;i<n;i++){//�������at
			for(int j=0;j<m;j++){
				at[i][j]=new VectorBal(a[i][j].getamp(),360-a[i][j].getpha());
			}
		}
		
		for(int i=0;i<a.length;i++){//����ת�þ���aT
			for(int j=0;j<a[i].length;j++){
				aT[j][i]=at[i][j];
			}
		}
		
		for(int i=0;i<aT.length;i++){//��aT*a
			for(int j=0;j<a[0].length;j++){
				VectorBal temp= new VectorBal(0,0);
				for(int k=0;k<a.length;k++){
					temp=temp.add(aT[i][k].mul(a[k][j]));
				    
				}
				aTa[i][j]=temp;
			}
		}
		
		VectorBal t=new VectorBal(0,0);
		for(int i=0;i<aT.length;i++){//��aT*b
			for(int j=0;j<a[0].length;j++){
				VectorBal temp= new VectorBal(0,0);
				for(int k=0;k<b.length;k++){
					temp=temp.add(aT[i][k].mul(b[k]));
				    aTb[i]=temp;
				}
				aTb[i]=t.sub(aTb[i]);//��-aT*b��
			}			
		}
		
		Guass gu = new Guass(aTa, aTb, aT.length);//���ø�˹��Ԫ����ⷽ�̣�
		x=gu.GuassCal(); 
		return x; 
	}
}
