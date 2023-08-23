package org.example.Collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(3);

        // Adding elements to the queue
        queue.add("Aasa");
        queue.add("Usha");
        queue.add("Nisha");



        boolean isAdded = queue.add("pisa");

        System.out.println("Is pisa added to the queue? " + isAdded);
        System.out.println("Queue: " + queue);


  /*      boolean containsElement = queue.contains("pisa");
        System.out.println("Queue contains pisa? " + containsElement);
        System.out.println("Queue: " + queue);*/



       /* System.out.println("Initial queue: " + queue);

        // Accessing the head of the queue
        String head = queue.peek();
        System.out.println("Head of the queue: " + head);

        // Removing elements from the queue
        String removedElement = queue.remove();
        System.out.println("Removed element: " + removedElement);

        // Checking if the queue contains an element
        boolean containsElement = queue.contains("Aasa");
        System.out.println("Queue contains Aasa? " + containsElement);

        // Checking if the queue is empty
        boolean isEmpty = queue.isEmpty();
        System.out.println("Is the queue empty? " + isEmpty);

        // Size of the queue
        int size = queue.size();
        System.out.println("Size of the queue: " + size);

        System.out.println("Final queue: " + queue);*/
    }
}
