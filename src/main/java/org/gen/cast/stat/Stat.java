package org.gen.cast.stat;

import org.gen.random.Dice;

/**
 * Created by Daniel on 04/05/2017.
 */
public class Stat {

    // Description of the stat.
    String description;

    // name holds the Name of the Stat
    String name;

    // string that is used to generate the stat.
    String generatedBy;

    // value of stat
    int value;

    public long generateValue() {
        if (!generatedBy.isEmpty()){
            value = (int)Dice.roll(generatedBy);
        }
        return value;
    }
}
