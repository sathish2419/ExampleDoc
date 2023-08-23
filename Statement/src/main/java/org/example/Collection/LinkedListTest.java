package org.example.Collection;

import java.util.LinkedList;

public class LinkedListTest {


    public static void main(String[] args)
    {
        LinkedList<String> list = new LinkedList<String>();
        list.add("Orange");
        list.add("Apple");
        list.add("Mango");
        list.add("Graphes");
        System.out.println(list);

        //add the string list
/*        list.add(0, "Watermelon");
        list.add(1, "Banana");
        System.out.println(list);*/

       // update the string element
        list.set(3,"carrot");
        System.out.println(list);

        //get the string element
       System.out.println(list.get(4));


/*
        //index of string element
        System.out.println(list.indexOf("carrot"));
        System.out.println(list.indexOf("Mango"));
        System.out.println(list.indexOf("Apple"));*/
             // print the String element in order
       /* for(String fruits:list)
            System.out.println(fruits);

        //remove the string element
        list.remove(5);
        System.out.println(list);*/
}
}

