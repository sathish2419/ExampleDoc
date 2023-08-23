package org.example.Abstract;

public class Bmw extends Car  {


 @Override

    public void engineSeret() {
 System.out.println("Bmw engine secret");


    }

   @Override

    public void companyVault(){
     System.out.println("Bmw companyVault");

    }



    public static void main(String[] args){
        Car car=new Bmw();
        car.engineSeret();
        car.companyVault();
    }


}