package org.example.Constructor;

public class Draw {

    String draw;

   Draw(){
        System.out.println("Draw object is created");
    }
    Draw(String toDraw){

        draw=toDraw;
        System.out.println("Drawing "+toDraw);
    }
    public static void main (String arg[]){
        Draw draw1=new Draw();
        Draw draw2=new Draw("circle");

    }
}
