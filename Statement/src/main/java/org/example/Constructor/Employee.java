package org.example.Constructor;

public class Employee {
    int empId;
    String name;

   //NO Arg constructor

    Employee(){
        empId=1;
        name="Agni";
        System.out.println("employee is inserted");


    }

    public static void main(String arg[]){

        Employee student= new Employee();

    }
}
