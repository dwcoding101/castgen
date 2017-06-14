package org.gen.dice;

import org.gen.random.Dice;

/**
 * Created by Daniel on 30/05/2017.
 */
public class TestDice {
    public static void main(String[] args) {

        for(int i=0; i<7; i++) {
            int number = (int) Dice.roll("3D10") + 25;
            System.out.println(number);

        }
        System.out.println("");
    }
}
