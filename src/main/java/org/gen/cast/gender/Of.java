package org.gen.cast.gender;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = Of.TYPE)
public class Of {
    public static final String TYPE = "OF_SEX";

    @GraphId
    private Long id;

    @StartNode
    private Genders start;

    @EndNode
    private Sex end;

    public Of(Genders start, Sex end) {
        this.start = start;
        this.end = end;
    }

    public Of() {
    }

    public Genders getStart() {

        return start;
    }

    public void setStart(Genders start) {
        this.start = start;
    }

    public Sex getEnd() {
        return end;
    }

    public void setEnd(Sex end) {
        this.end = end;
    }
}
