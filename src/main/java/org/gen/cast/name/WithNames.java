package org.gen.cast.name;

import org.gen.cast.gender.Sex;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */
@RelationshipEntity(type = WithNames.TYPE)
public class WithNames {
    public static final String TYPE = "CALLED";

    @GraphId
    private Long id;

    @StartNode
    private Sex start;

    @EndNode
    private Names end;

    public WithNames(Sex start, Names end) {
        this.start = start;
        this.end = end;
    }

    public Sex getStart() {
        return start;
    }

    public void setStart(Sex start) {
        this.start = start;
    }

    public Names getEnd() {
        return end;
    }

    public void setEnd(Names end) {
        this.end = end;
    }

    public WithNames() {

    }
}
