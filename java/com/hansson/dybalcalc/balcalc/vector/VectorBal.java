package com.hansson.dybalcalc.balcalc.vector;

import java.text.DecimalFormat;

/** 
 * @author Hansson He
 * @Date�� 2016.11.18
 */  
  
public class VectorBal { // 
    double amp;  // ��ֵ 
    double pha;  // ��λ
  
    public VectorBal(double amp,double pha){ // �������Ĺ��췽��  
        this.amp = amp;  
        this.pha = pha;  
    }  
  
    public VectorBal() {  
        // TODO Auto-generated constructor stub  
    }  
  
    public double getamp() {  
        return amp;  
    }  
  
    public void setamp(double amp) {  
        this.amp = amp;  
    }  
  
    public double getpha() {  
        return pha;  
    }  
  
    public void setpha(double pha) {  
        this.pha = pha;  
    }  
      
    public VectorBal add(VectorBal vectorBal){ // ʸ�����  
        double vecAmp = vectorBal.getamp();  
        double vecPha = vectorBal.getpha();  
        double Acos=vecAmp*Math.cos(vecPha*Math.PI/180)+amp*Math.cos(pha*Math.PI/180);
        double Asin=vecAmp*Math.sin(vecPha*Math.PI/180)+amp*Math.sin(pha*Math.PI/180);
        double ampt=Math.pow(Acos*Acos+Asin*Asin,0.5);
        double theta=(thetaCal(Acos,Asin)+360)%360;

        return new VectorBal(ampt,theta);  
    }  
      
    public VectorBal sub(VectorBal vectorBal){ // ʸ�����  
        double vecAmp = vectorBal.getamp();  
        double vecPha = vectorBal.getpha();  
        double Acos=amp*Math.cos(pha*Math.PI/180)-vecAmp*Math.cos(vecPha*Math.PI/180);
        double Asin=amp*Math.sin(pha*Math.PI/180)-vecAmp*Math.sin(vecPha*Math.PI/180);
        double ampt=Math.pow(Acos*Acos+Asin*Asin,0.5);
        double theta=thetaCal(Acos,Asin);

        return new VectorBal(ampt,theta);   
    }  
      
    public VectorBal mul(VectorBal vectorBal){ // ʸ�����  
        double vecAmp = vectorBal.getamp();  
        double vecPha = vectorBal.getpha();  
        double ampt = amp*vecAmp;  
        double theta = (pha+vecPha+360)%360;
        return new VectorBal(ampt,theta);  
    }  
    
    public VectorBal div(VectorBal vectorBal){ // ʸ�����  
        double vecAmp = vectorBal.getamp();  
        double vecPha = vectorBal.getpha();  
        double ampt = amp/vecAmp;  
        double theta = (pha-vecPha+360)%360;
        
        return new VectorBal(ampt,theta); 
    }  
    
    public double thetaCal(double rl,double im){
    	double radian;
    	if(Math.abs(rl)<0.00000001){
    	   if(im>0){
    	      radian=Math.PI/2;
    	   }
    	   else{
    	      radian=-Math.PI/2;
    	   }
    	}
    	else {
    	      radian=Math.atan(im/rl);
    	      if(rl<0){ 
    	    	radian=radian+Math.PI;
    	      }
    	}  
    	
    	if((radian*180/Math.PI)<0){
    	   return (radian*180/Math.PI+360) % 360;
    	}
    	else{
    	   return radian*180/Math.PI;
    	}
     }
    
    public void print(){ // 
        String ampStr = new DecimalFormat("#.####").format(amp);
        String phaStr = new DecimalFormat("#.####").format(pha);
        System.out.println(ampStr+"∠"+phaStr+"°");  

    }  
    
    public VectorBal format(){
		String ampStr = new DecimalFormat("#.#").format(amp);
		String phaStr = new DecimalFormat("#.#").format(pha);
	    VectorBal vectorBal = new VectorBal(Double.parseDouble(ampStr),
				                            Double.parseDouble(phaStr));
		return vectorBal;
	   
    }
   
}
