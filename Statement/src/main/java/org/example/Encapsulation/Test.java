package org.example.Encapsulation;

public class Test{

    private int empid;

    public void setEmpid(int empid1){
        empid=empid1;
    }

    public int getEmpid(){
        return empid;
    }

    public static void main(String args []){
        Test obj=new Test();
        obj.setEmpid(1);
        obj.getEmpid();
    }

}
