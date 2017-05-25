package org.gen.curency;

/**
 * Created by Daniel on 20/05/2017.
 */
public class Sterling {
    private static int pound;        //creating private attributes
    private static int shilling;
    private static int pence;


    public Sterling()         //default constructor that takes all values and initializes to 0
        {
            pound = 0;
            shilling = 0;
            pence = 0;

        }
    public Sterling(int p, int sh, int d)    //overloaded constructor that takes all values and initializes to user defined
        {
            pound = p;
            shilling = sh;
            pence = d;

            if(pound<0)                        //verifying condition when initialized
            {
                System.out.println("Sorry, you have entered an incorrect value");
                pound = 0;
            }
            else
            {
                pound = p;
            }

            if(shilling<0)    //verifying condition when initialized
            {
                System.out.println("Sorry, you have entered an incorrect shilling amount");
                shilling = 0;
            }
            else
            {
                shilling = sh;
            }

            if(pence<0)            //verifying condition when initialized
            {
                System.out.println("Sorry, you have entered an incorrect pence amount");
                pence = 0;
            }
            else
            {
                pence = d;
            }
        }//closes public Sterling (int lb, int sh, int p)

        //******************************************************************************
        //begins my accessor methods

    public int getPound()        //accessor method will return pound
    {
        return pound;
    }

    public int getShilling()    //accessor method will return shilling
    {
        return shilling;
    }

    public int getPence()        //accessor method will return pence
    {
        return pence;
    }

    //begins my mutator methods

    public void setPound(int p)            //mutator method will set to given pound
    {
        if(pound<0)
        {
            System.out.println("Sorry, you have entered an incorrect value");
            pound = 0;
        }
        else
        {
            pound = p;
        }

    }

    public void setShilling(int sh)    //mutator method will set to given shilling
    {
        if(shilling<0)
        {
            System.out.println("Sorry, you have entered an incorrect shilling amount");
            shilling = 0;
        }
        else
        {
            shilling = sh;
        }
    }

    public void setPence(int d)        //mutator method will set to given shilling
    {
        if(pence<0)
        {
            System.out.println("Sorry, you have entered an incorrect pence amount");
            pence = 0;
        }
        else
        {
            pence = d;
        }
    }




    public boolean equals(Sterling c)                                //method to compare 2 sterling objects
    {
        if(pound==(pound) && shilling==(shilling) && pence==(pence))
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public int compareTo(Sterling b)        //methods to compare 2 sterling methods and return integer values
    {
        if((this.getPound()< b.getPound())&& (this.getShilling()< b.getShilling() )&& (this.getPence()< b.getPence()))
        {
            return -1;
        }
        else if(this.getPound()== b.getPound()&& this.getShilling()== b.getShilling()&& this.getPence()== b.getPence())
        {
            return 0;
        }
        else//(this.getPound()> this.getPound()&& this.getShilling()> b.getShilling()&& this.getPence()> b.getPence())
        {
            return 1;
        }
    }


    public String toString()             //will convert all the values to string when this method is invoked
    {

        String poundsString = "";
        String shillingString = "";
        String penceString = "";

        if(pound == 0) {

        }
        return ("£" +pound + "." + shilling +"s."+ pence + "d.");
    }


    public void showInfo()                //will show all attributes
    {
        System.out.println("This Sterling object has " + this.getPound() + " pounds, " + this.getShilling() + " shillings, " + this.getPence() + " pence." );
    }

/*
    public static int toPence(Sterling a)
    {
        double convertedPence;

        double poundToPence = a.getPound();                            will get number of pounds
        poundToPence*=240;                                                    //will convert to number of pence

        double shillingToPence = a.getShilling();                            //will get number of shilling
        shillingToPence*=12;                                                //will convert to pence

        double penceToPence = a.getPence();                                    //will get number of pence

        convertedPence = poundToPence + shillingToPence + penceToPence;        //will hold the total number of pence

        double tax = convertedPence*(0.08);                                    //will calculate 8% tax

        convertedPence = (convertedPence + tax);                            //adds tax to pence value



        return ((int)convertedPence);                                        //returns pence value with tax

    }


    */


    public static int add(Sterling a, Sterling b)
    {

        double aConvertedPence;

        double aPoundToPence = a.getPound();                                    //will get number of pounds
        aPoundToPence*=240;                                                        //will convert to number of pence

        double aShillingToPence = a.getShilling();                                //will get number of shilling
        aShillingToPence*=12;                                                    //will convert to pence

        double aPenceToPence = a.getPence();                                    //will get number of pence

        aConvertedPence = aPoundToPence + aShillingToPence + aPenceToPence;        //will hold the total number of pence

        double aTax = aConvertedPence*(0.08);                                    //will calculate 8% tax

        aConvertedPence = (aConvertedPence + aTax);                                //adds tax to pence value



        double bConvertedPence;

        double bPoundToPence = b.getPound();                                    //will get number of pounds
        bPoundToPence*=240;                                                        //will convert to number of pence

        double bShillingToPence = b.getShilling();                                //will get number of shilling
        bShillingToPence*=12;                                                    //will convert to pence

        double bPenceToPence = b.getPence();                                    //will get number of pence

        bConvertedPence = bPoundToPence + bShillingToPence + bPenceToPence;        //will hold the total number of pence

        double bTax = bConvertedPence*(0.08);                                    //will calculate 8% tax

        bConvertedPence = (bConvertedPence + bTax);                                //adds tax to pence value




        int totalSterling = ((int)(aConvertedPence +bConvertedPence ));


        return totalSterling;                                        //returns pence value with tax

    }

    //need same method for subtract


    public static int sub(Sterling a, Sterling b)
    {

        double aConvertedPence;

        double aPoundToPence = a.getPound();                                    //will get number of pounds
        aPoundToPence*=240;                                                        //will convert to number of pence

        double aShillingToPence = a.getShilling();                                //will get number of shilling
        aShillingToPence*=12;                                                    //will convert to pence

        double aPenceToPence = a.getPence();                                    //will get number of pence

        aConvertedPence = aPoundToPence + aShillingToPence + aPenceToPence;        //will hold the total number of pence

        double aTax = aConvertedPence*(0.08);                                    //will calculate 8% tax

        aConvertedPence = (aConvertedPence + aTax);                                //adds tax to pence value



        double bConvertedPence;

        double bPoundToPence = b.getPound();                                    //will get number of pounds
        bPoundToPence*=240;                                                        //will convert to number of pence

        double bShillingToPence = b.getShilling();                                //will get number of shilling
        bShillingToPence*=12;                                                    //will convert to pence

        double bPenceToPence = b.getPence();                                    //will get number of pence

        bConvertedPence = bPoundToPence + bShillingToPence + bPenceToPence;        //will hold the total number of pence

        double bTax = bConvertedPence*(0.08);                                    //will calculate 8% tax

        bConvertedPence = (bConvertedPence + bTax);                                //adds tax to pence value




        int totalSterling = ((int)(aConvertedPence -bConvertedPence ));

        if((aConvertedPence -bConvertedPence <=0))
        {
            return totalSterling = 0;
        }
        else
        {
            return totalSterling;
        }

    }


    public static int toPence(int a)
    {
        int pence = a%12;        //will give the remainder
        return pence;
    }

    public static int toShilling(int a)
    {
        int shillingValue = a/12;
        shillingValue%=20;
        return shillingValue;
    }

    public static int toPound(int a)
    {
        int shillingValue = a/12;
        int poundValue = shillingValue/20;
        return poundValue;
    }



}// end of class sterling



