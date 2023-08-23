package org.example.Abstract;

public class Benz extends Car implements UpComingProject{
    @Override
    public void engineSeret() {
       System.out.println("Benz engine seret");

    }

   @Override
    public void companyVault(){
        System.out.println("Benz companyVault");
    }
    public static void main(String[] args){
        Car car=new Benz();
        car.engineSeret();
        car.companyVault();
    }

    @Override
    public void Employee() {

    }
}
