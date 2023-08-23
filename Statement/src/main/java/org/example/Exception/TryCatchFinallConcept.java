package org.example.Exception;

public class TryCatchFinallConcept {

    public static int dummy(){
        return 3;
    }


    public static void main(String args []){

       try {
           System.out.println("Insert try block");
       /*    System.exit(0);*/
           System.out.println(2/0);
           System.out.println("Arthimetic Exception");

       }catch (Exception e){
           System.out.println("inside catch block");
       }finally {
           System.out.println("Finally block ");
       }

    }
}
