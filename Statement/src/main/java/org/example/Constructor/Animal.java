package org.example.Constructor;

public class Animal {

    String animal_name;
    String animal_type;

    //NO Arg constructor

    Animal(String name,String type) {

       this.animal_name = name;
        animal_type = type;
    }
    public void sayAboutAnimal(){
        System.out.println("Animal name is "+animal_name+" and the type is " +animal_type);
    }

    public static void main(String arg[]){

        Animal animal1= new Animal("lion","Omnivorus");


        animal1.sayAboutAnimal();

    }

}
