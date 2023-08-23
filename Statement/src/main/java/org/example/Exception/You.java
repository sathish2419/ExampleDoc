package org.example.Exception;

public class You {
    public void makeYourGirlLaugh(){
        try{
            throw new MachaInterrupedException ("machan is coming to hit you");

        }catch (MachaInterrupedException e){
            e.printStackTrace();
         /*   System.out.println("Myfriend: macha give me a pose da");
            System.out.println("and you are acting like you are posing for instagram ");*/
        }
    }
    public static void main (String arg[]){
        You you=new You();
        you.makeYourGirlLaugh();
    }
}
