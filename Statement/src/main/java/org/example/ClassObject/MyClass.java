package org.example.ClassObject;

class MyClass {
    public int number;
    public String name;


    // Constructor
    public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }


    // Method to print the object's state
    public void printDetails() {
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
    }

    public static void main(String[] args) {


        // Creating an object of MyClass using the constructor
        MyClass myObject = new MyClass(1, "John");


        // Accessing instance variables using the object and printing details
        myObject.printDetails();
    }
}
