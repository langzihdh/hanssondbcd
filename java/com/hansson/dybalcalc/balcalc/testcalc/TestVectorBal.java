package com.hansson.dybalcalc.balcalc.testcalc;

import com.hansson.dybalcalc.balcalc.vector.VectorBal;

/** 
 * @author Kun Sun 
 * @Date�� 2013.10.17 
 */  
public class TestVectorBal { // ���ڲ��Ը�����  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
          
        VectorBal complex1 = new VectorBal(321,222);  
        VectorBal complex2 = new VectorBal(143,32);
        VectorBal tempa=complex1.add(complex2);
        VectorBal temps=complex1.sub(complex2);
        VectorBal tempp=complex1.mul(complex2);
        VectorBal tempd=complex1.div(complex2);
        // ���²����е�talk()�еĽ���Ϊ  
        // talk(string data1_1,string data1_2,string data2_1,string data2_2,string operation);  
        // data1_1,data1_2����Ϊ��һ��������ʵ�����鲿  
        // data2_1,data2_2����Ϊ�ڶ���������ʵ�����鲿  
        // operationΪ������������Ĳ�����,ÿ�ֲ��Զ����Ӽ��˳�  
          
        tempa.print();
        temps.print();
        tempp.print();
        tempd.print();// һ�����  
         
          
       
    }  
}  
