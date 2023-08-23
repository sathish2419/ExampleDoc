package org.example.Encapsulation;

public class Main {


    public static void main(String[] args) {

        // Creating a Student object
        Student student = new Student("John Doe", 20, "12345");

        // Accessing the object's state using getter methods
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Student ID: " + student.getStudentId());

        // Modifying the object's state using setter methods
        student.setName("Jane Smith");
        student.setAge(21);
        student.setStudentId("67890");

        // Displaying the updated state
        System.out.println("\nUpdated Details:");
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Student ID: " + student.getStudentId());
    }
}
