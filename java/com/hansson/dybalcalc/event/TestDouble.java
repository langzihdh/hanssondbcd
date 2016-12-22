

package com.hansson.dybalcalc.event;

public class TestDouble {
	 public static void main(String[] args) {  
		 String str= "11.1";
		 IsDouble is = new IsDouble(str);
		 if(is.isDouble()){
			 System.out.println("yes");
		 }
		 else{
			 System.out.println("no");
		 }
	 }
}
