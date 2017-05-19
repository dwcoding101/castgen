package org.gen.dates;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 10/05/2017.
 */
@NodeEntity(label=Months.LABEL)
public class Months {

    @GraphId
    private Long id;

    public static final String LABEL = "MONTHS_OF_THE_YEAR";
    @Relationship(type="CAN_BE", direction=Relationship.OUTGOING)
    List<Month> months = new ArrayList<>();

    public void addMonthOfTheYear(Month month) {
        months.add(month);
    }
}
