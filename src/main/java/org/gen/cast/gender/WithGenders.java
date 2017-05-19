package org.gen.cast.gender;

import org.gen.cast.race.Race;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Daniel on 04/05/2017.
 */
@RelationshipEntity(type = WithGenders.TYPE)
public class WithGenders {
    @Transient
    public static final String TYPE = "WITH_GENDERS";

    @GraphId
    private Long id;

    @StartNode
    private Race start;

    public WithGenders() {

    }

    public Race getStart() {

        return start;
    }

    public void setStart(Race start) {
        this.start = start;
    }

    public Genders getEnd() {
        return end;
    }

    public void setEnd(Genders end) {
        this.end = end;
    }

    @EndNode
    private Genders end;

    public WithGenders(Race start, Genders end) {
        this.start = start;
        this.end = end;
    }
}
