package com.hansson.dybalcalc.balcalc.calc;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class MultiPlaneCal {  
  
    /** 
     * @����Ԫ��˹��ȥ�� 
     */  
	static VectorBal factor;
	static VectorBal sum;  
	static int n;
	static int k;
	static int j;
	static int t;
    //static int n;  
    static int n2; //��¼���еĴ���  
    static VectorBal a[][];  
    static VectorBal b[];  
    static VectorBal x[];  

    public static VectorBal[] GuassCal(VectorBal aa[][],VectorBal bb[], int n){   
    	a=aa;
    	b=bb;
    	x=new VectorBal[n];
    	for(k=0;k<=n-2;k++)  
    	{     
    		for(int i=k+1;i<=n-1;i++)   
    		{      
    			factor = a[i][k].div(a[k][k]);     
    		    for(j=k+1;j<n;j++){       
    			   a[i][j] = a[i][j].sub(factor.mul(a[k][j]));    
    			}     
    		    b[i] = b[i].sub(factor.mul(b[k]));
    		}
    	}
             x[n-1]= b[n-1].div(a[n-1][n-1]);    
             // System.out.println("x"+t+"="+x[n-1]);  
             x[n-1].print();;
               for(int i=n-2;i>=0;i--)   { 
	             t = i+1;    
	             sum = b[i];     
	              for(j=i+1;j<=n-1;j++)    { 
		                sum = sum.sub(a[i][j].mul(x[j]));    
		                }    
	                  x[i] = sum.div(a[i][i]);   
	                 // System.out.println("x"+t+"="+x[i]);   
	                  x[i].print();
	                  }
			return x; 
                } 
}  