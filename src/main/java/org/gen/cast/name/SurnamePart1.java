package org.gen.cast.name;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label = SurnamePart1.TYPE)
public class SurnamePart1 {
    public static final String TYPE = "SURNAME_PART_1";



    @GraphId
    private Long id;

    @Property
    private Long number;

    // RacialDescription of the stat.
    @Property(name="name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public SurnamePart1(String name, long number) {

        this.number = (Long) number;
        this.name = name;
    }

    public SurnamePart1() {

    }
}
