package org.example.Constructor;

public class Student {
    String student_name;
    int roll_no;

    //do u see any constructor here ?
    // compiler has created one here .

    public static void main(String arg[]){
        Student student= new Student();
        System.out.println(student.student_name);
        System.out.println(student.roll_no);
    }
}
