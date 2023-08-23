package org.example.Exception;

public class SingleCatchException {
    public static void main(String args[]){
        /*System.out.println(2/0);*/

        try{
            int num1=3;
            int num2=6;
            int result =num2/num1;
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("drink the lemon juice and don't divide the num by zero");
        }



    }
}
