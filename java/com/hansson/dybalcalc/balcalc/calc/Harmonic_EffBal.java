package com.hansson.dybalcalc.balcalc.calc;

import java.util.ArrayList;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

public class Harmonic_EffBal {

	    public ArrayList <VectorBal> list = new ArrayList<VectorBal>();
	    public ArrayList <VectorBal> har = new ArrayList<VectorBal>();
	    boolean n;
	   
	    public Harmonic_EffBal(ArrayList list, boolean n){ // �������Ĺ��췽��  
	        this.list = list; 
	        this.n=n;

	    }  
	  
	    public Harmonic_EffBal() {  
	        // TODO Auto-generated constructor stub  
	    }  
	  
	    public ArrayList<VectorBal> getlist() {  
	        return list;  
	    }  
	  
	    public void  setlist(ArrayList list) {  
	        this.list = list;  
	    }  
	    
	    public ArrayList<VectorBal> getHar() {
			return har;
		}

		public void setHar(ArrayList<VectorBal> har) {
			this.har = har;
		}

		public void harmonicCal(){
	    	VectorBal temp=new VectorBal(0,0);
	    	VectorBal A0=(VectorBal) list.get(0);
	    	VectorBal B0=(VectorBal) list.get(1);
	    	VectorBal P1=(VectorBal) list.get(2);
	    	VectorBal P2=(VectorBal) list.get(3);
	    	VectorBal A1=(VectorBal) list.get(4);
	    	VectorBal B1=(VectorBal) list.get(5);
	    	VectorBal A0z=new VectorBal();
	    	VectorBal A0f=new VectorBal();
	    	VectorBal Pz=new VectorBal();
	    	VectorBal Pf=new VectorBal();
	    	VectorBal A1z=new VectorBal();
	    	VectorBal A1f=new VectorBal();
	    	VectorBal Az=new VectorBal();
	    	VectorBal Af=new VectorBal();
	    	VectorBal Qz=new VectorBal();
	    	VectorBal Qf=new VectorBal();
	    	VectorBal Qa=new VectorBal();
	    	VectorBal Qb=new VectorBal();
	    	
	    	A0z=A0.add(B0);
	    	A0z=new VectorBal(A0z.getamp()*0.5,A0z.getpha());
	    	A0f=A0.sub(B0);
	    	A0f=new VectorBal(A0f.getamp()*0.5,A0f.getpha());

	    	Pz=P1.add(P2);
	    	Pz=new VectorBal(Pz.getamp()*0.5,Pz.getpha());
	    	Pf=P1.sub(P2);
	    	Pf=new VectorBal(Pf.getamp()*0.5,Pf.getpha());

	    	
	    	A1z=A1.add(B1);
	    	A1z=new VectorBal(A1z.getamp()*0.5,A1z.getpha());
	    	A1f=A1.sub(B1);
	    	A1f=new VectorBal(A1f.getamp()*0.5,A1f.getpha());
	    	
	        Az=(A1z.sub(A0z)).div(Pz);
	        Af=(A1f.sub(A0f)).div(Pf);
	        if(n){
		        Qz=temp.sub(A0z).div(Az);
		    	Qf=temp.sub(A0f.div(Af));
	        }
	        else{
		        Qz=temp.sub(A1z).div(Az);
		    	Qf=temp.sub(A1f.div(Af));
	        }

	    	Qa=Qz.add(Qf);
	    	Qb=Qz.sub(Qf);
	    	
	    	har.add(A0z);
	    	har.add(Pz);
	    	har.add(A1z);
	    	har.add(Az);
	    	har.add(Qz);
	    	har.add(Qa);
	    	har.add(A0f);
	    	har.add(Pf);
	    	har.add(A1f);
	    	har.add(Af);
	    	har.add(Qf);
	    	har.add(Qb);
	    }
}
