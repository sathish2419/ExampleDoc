package org.example.Polymorphisam;

public class TheWayWeTalk {

    void talk(Parent styleOfTaking){
        System.out.println("Polite");


    }
    void talk(Friend styleOfTaking){
        System.out.println("Open type");

    }

    public static void main (String args[]){

        TheWayWeTalk talk =new TheWayWeTalk();

        Parent parent=new Parent();
        talk.talk(parent);
        Friend friend =new Friend();
        talk.talk(friend);

    }
}
