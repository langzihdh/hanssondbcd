/**
 * 
 */
package com.hansson.dybalcalc.balcalc.calc;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

/**
 * @author Hansson
 *
 */
public class DoublePlaneBal {

	    private ArrayList <VectorBal> list = new ArrayList<VectorBal>();
	    private boolean ta, tb;
		private ArrayList <VectorBal> dpb = new ArrayList<VectorBal>();
	   
	    public DoublePlaneBal(ArrayList list, boolean ta, boolean tb){ // �������Ĺ��췽��  
	        this.list = list;  
	        this.ta = ta;  
	        this.tb = tb;  

	    }  
	  
	    public DoublePlaneBal() {  
	        // TODO Auto-generated constructor stub  
	    }  
	  
	    public ArrayList<VectorBal> getlist() {  
	        return list;  
	    }  
	  
	    public void  setlist(ArrayList list) {  
	        this.list = list;  
	    }  
	    
	    
	    public ArrayList<VectorBal> doublePlaneCal(){ 
	    	
	    	VectorBal temp=new VectorBal(0,0);
	    	VectorBal A0=(VectorBal) list.get(0);
	    	VectorBal B0=(VectorBal) list.get(1);
	    	VectorBal T1=(VectorBal) list.get(2);
	    	VectorBal A1=(VectorBal) list.get(3);
	    	VectorBal B1=(VectorBal) list.get(4);
	    	VectorBal T2=(VectorBal) list.get(5);
	    	VectorBal A2=(VectorBal) list.get(6);
	    	VectorBal B2=(VectorBal) list.get(7);
	    	
	    	VectorBal a11=A1.sub(A0).div(T1);
	    	VectorBal a21=B1.sub(B0).div(T1);
	    	VectorBal a12=new VectorBal();
	    	VectorBal a22=new VectorBal();
	    	VectorBal u11=new VectorBal();
	    	VectorBal u12=new VectorBal();
	    	VectorBal u21=new VectorBal();
	    	VectorBal u22=new VectorBal();
	    	
	    	if(ta){
		    	 a12=A2.sub(A0).div(T2);
		    	 a22=B2.sub(B0).div(T2);
	    	}
	    	else{
		    	 a12=A2.sub(A1).div(T2);
		    	 a22=B2.sub(B1).div(T2);
	    	}


	    	VectorBal deta=(a11.mul(a22)).sub(a12.mul(a21));
	    	VectorBal deta1=(B0.mul(a12).sub(A0.mul(a22)));
	    	VectorBal deta2=(A0.mul(a21).sub(B0.mul(a11)));

	    	if(ta){
	    		 u11=deta1.div(deta);  		 
	    	}
	    	else{
	    		 u11=(deta1.div(deta)).sub(T1);
	    	}
	    	
	    	
	    	if(tb){
	    		u22=deta2.div(deta);
	    	}
	    	else{
	    		u22=(deta2.div(deta)).sub(T2);
	    	}
	    	dpb.add(a11);
	    	dpb.add(a21);
	    	dpb.add(u11);
	    	
	    	dpb.add(a12);
	    	dpb.add(a22);
	    	dpb.add(u22);

	    	return dpb; 

	    }

		public ArrayList getDpb() {
			return dpb;
		}

		public void setDpb(ArrayList dpb) {
			this.dpb = dpb;
		}
	    
}
