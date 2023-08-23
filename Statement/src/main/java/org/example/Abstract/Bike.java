package org.example.Abstract;

public   class Bike{

   void run(){
       System.out.println("bike is running safely..");
   }

      void changeGear(){
          System.out.println("change bike gear..");
      }
    public static void main (String args[]){
        Bike obj= new Bike();
        obj.run();
        obj.changeGear();
    }


}