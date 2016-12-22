package com.hansson.dybalcalc.balcalc.calc;


import com.hansson.dybalcalc.balcalc.vector.VectorBal; 
public class Guass {   
	private VectorBal a[][],b[],x[],factor,sum;  
	private int n; 
	
	public Guass() {
		// TODO Auto-generated constructor stub
	}
	public Guass(VectorBal[][] a, VectorBal[] b, int n) {
		this.a=a;
		this.b=b;
		this.n=n;

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

	public VectorBal[] GuassCal(){ 
         int k,j;
		 x=new VectorBal[n];
		 /*--------------��Ԫ����--------------------*/
		 for(k=0;k<=n-2;k++){  //����Ϊ��Ԫ���̣�     
			 for(int i=k+1;i<=n-1;i++){    
				 factor = a[i][k].div(a[k][k]);     
				 for(j=k+1;j<n;j++){
					 a[i][j] = a[i][j].sub(factor.mul(a[k][j]));     
				 }     
				 b[i] = b[i].sub(factor.mul(b[k])); 
			  }
		  }
		 /*----------------�ش�����-------------------------*/
          x[n-1]= b[n-1].div(a[n-1][n-1]);//����Ϊ�ش����̣�
          for(int i=n-2;i>=0;i--){   
        	  sum = b[i];     
        	  for(j=i+1;j<=n-1;j++){    
        		  sum = sum.sub(a[i][j].mul(x[j]));    
        	  }     
        	  x[i] = sum.div(a[i][i]);
          }
		  return x;  //���ط��̽⣻
      } 
   } 

