package org.gen.dates;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 10/05/2017.
 */

@NodeEntity(label = Day.LABEL)
public class Day {
    public static final String LABEL ="DAY_OF_THE_WEEK";

    @GraphId
    private Long id;

    Long number;
    String nameOfDay;

    public Day() {
    }

    public String getNameOfDay() {
        return nameOfDay;
    }

    public Day(Long number, String nameOfDay) {

        this.number = number;
        this.nameOfDay = nameOfDay;
    }
}
