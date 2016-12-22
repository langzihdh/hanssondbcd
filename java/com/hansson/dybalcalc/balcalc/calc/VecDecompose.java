package com.hansson.dybalcalc.balcalc.calc;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class VecDecompose {
	  public VectorBal vec1,vec2,vecVZ,vecVF;

	    public VecDecompose(VectorBal vec1,VectorBal vec2 ){ // �������Ĺ��췽��  
	        this.vec1 = vec1;  
	        this.vec2 = vec2; 

	    }  
	  
	    public VecDecompose() {  
	        // TODO Auto-generated constructor stub  
	    }  
	  
	    public VectorBal getvec1() {  
	        return vec1;  
	    }  
	  
	    public void setvec1(VectorBal vec1) {  
	        this.vec1 = vec1;  
	    }  
	  
	    public VectorBal getvec2() {  
	        return vec2;  
	    }  
	  
	    public void setvec2(VectorBal vec2) {  
	        this.vec2 = vec2;  
	    }
	    public VectorBal getvecVZ() {  
	        return vecVZ;  
	    }  
	  
	    public void setvecVZ(VectorBal vecVZ) {  
	        this.vecVZ = vecVZ;  
	    }
	    public VectorBal getvecVF() {  
	        return vecVF;  
	    }  
	    
	    public void setvecVF(VectorBal vecVF) {  
	        this.vecVF = vecVF;  
	    }
	    
	    public VectorBal vecVZCal(){
	    	vecVZ=vec1.add(vec2);
	    	vecVZ=new VectorBal(vecVZ.getamp()*0.5,vecVZ.getpha());
			return vecVZ;
	    }
	    
	    public VectorBal vecVFCal(){
	    	vecVF=vec1.sub(vec2);
	    	vecVF=new VectorBal(vecVF.getamp()*0.5,vecVF.getpha());
			return vecVF;
	    }
	    
}
