package org.example.Collection;

import java.util.ArrayList;


public class CollectionArrayList {
    public static void main(String args []){


      /*  int a[]=new int[8]; */ // fixed size

       ArrayList<Integer> alist=new ArrayList<>();//flexible size
       for (int i=1;i<=10;i++)
           alist.add(i);
        System.out.println(alist);

/*
       ArrayList<String> slist=new ArrayList<String>();
        slist.add("sathish");
        System.out.println(slist);
*/

/*        ArrayList list=new ArrayList();
        list.add(1);
        list.add("vasanth");
        list.add("t");
        System.out.println(list);*/


/*        System.out.println(alist.get(3));
        alist.set(0,20);
        System.out.println(alist);*/

/*
        alist.remove(0);
        System.out.println(alist);
*/



        for (int i:alist)
            System.out.println(i);





    }
}
