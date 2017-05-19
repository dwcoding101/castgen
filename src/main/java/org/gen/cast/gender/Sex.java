package org.gen.cast.gender;

import org.gen.cast.name.*;
import org.gen.cast.name.WithNames;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label=Sex.TYPE)
public class Sex {

    public static final String TYPE = "SEX";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @GraphId
    private Long id;

    @Property(name="type")
    private String type;

    @Relationship(type=WithNames.TYPE)
    private Set<WithNames> with = new HashSet<>();

    public void addNames(Names target) {
        this.with.add(new WithNames(this, target));
    }

    public Sex(String type) {
        this.type = type;
    }


    public Names getNames () {
        return with.stream().findFirst().get().getEnd();
    }

    public Sex() {
    }
}
