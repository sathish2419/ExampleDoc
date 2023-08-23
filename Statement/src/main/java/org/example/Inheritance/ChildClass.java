package org.example.Inheritance;

public class ChildClass extends ParentClass {

  ChildClass(){
      System.out.println("Child constructor");
  }
    public static void main(String args[]){
        ChildClass childClass=new ChildClass();
    }
}
