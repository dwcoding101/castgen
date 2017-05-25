package org.gen.curency;

/**
 * Created by Daniel on 20/05/2017.
 */
public class English_Currency {

    public static void main(String[] args) {

        Sterling c1 = new Sterling(1,2,3);
        Sterling c2 = new Sterling(4,5,6);


        int c1Pound = c1.getPound();
        int c1Shilling = c1.getShilling();
        int c1Pence = c1.getPence();


        int c2Pound = c2.getPound();
        int c2Shilling = c2.getShilling();
        int c2Pence = c2.getPence();


        System.out.println("Sterling object 1 with " + c1Pound + " pounds and " + c1Shilling + " shillings and " + c1Pence+" pence");

        System.out.println("Sterling object 2 with " + c2Pound + " pounds and " + c2Shilling + " shillings and " + c2Pence +" pence");


    }
}
