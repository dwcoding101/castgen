package org.gen.cast.stat;

import org.gen.random.Dice;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label = Stat.TYPE)
public class Stat {
    public static final String TYPE = "STAT";



    @GraphId
    private Long id;

    // RacialDescription of the stat.
    @Property(name="description")
    private String description;

    // name holds the Name of the Stat used in the NPC Stat.
    @Property(name="name")
    private String name;

    // order is used for order the output.
    @Property(name="order")
    private int order;

    public Stat() {
    }

    // string that is used to generate the stat.
    @Property(name="generatedBy")
    private String generatedBy;

    // value of stat
    @Property(name="value")
    private int value;

    public long generateValue() {
        if (!generatedBy.isEmpty()){
            if(generatedBy.contains("DerivedFrom")||generatedBy.contains("RandomTableSelect")) {

            } else {
                value = (int) Dice.roll(generatedBy);
            }
        }
        return value;
    }

    public Stat(String name, int order, String generatedBy) {
        this.name = name;
        this.order = order;
        this.generatedBy = generatedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public int getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Stat(Stat copy) {
        this.generatedBy = copy.generatedBy;
        this.description = copy.description;
        this.value = copy.value;
        this.name = copy.name;
        this.order = copy.order;
    }
}
