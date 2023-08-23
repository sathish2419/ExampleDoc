package org.example.Polymorphisam;

public class Addition {

    int add(int n1, int n2){
        return n1+n2;
    }

    int add(int n1, int n2,int n3 ){
        return n1+n2+n3;
    }

    float add(float n1, float n2){
        return n1+n2;
    }


    public static void main (String args []){

        Addition obj =new Addition();
        System.out.println("Sum of two num: "+obj.add(20,10));

        System.out.println("Sum of three num: "+obj.add(20,10,30));

        System.out.println("Sum of float num: "+obj.add((float) 10.2, (float) 1.0));

    }
}
