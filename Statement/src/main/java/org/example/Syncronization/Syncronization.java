package org.example.Syncronization;

public class Syncronization {
    public static void main(String arg[]){
        Table obj =new Table();
        MyThread1 t1 =new MyThread1(obj);
        MyThread2 t2=new MyThread2(obj);
        t1.start();
        t2.start();
    }
}
