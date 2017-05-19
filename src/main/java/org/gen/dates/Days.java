package org.gen.dates;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/05/2017.
 */
@NodeEntity(label="DAYS_OF_THE_WEEK")
public class Days {
    public static final String LABEL = "DAYS_OF_THE_WEEK";

    @GraphId
    private Long id;

    @Relationship(type="CAN_BE", direction=Relationship.OUTGOING)
    List<Day> days = new ArrayList<>();

    public void addDayOfTheWeek(Day day) {
        days.add(day);
    }

    public int getNumberDaysInTheWeek(){
        return days.size();
    }
}
