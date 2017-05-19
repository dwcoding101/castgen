package org.gen.cast.name;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = Of.TYPE)
public class Of {
    public static final String TYPE = "OF_FIRSTNAMES";

    @GraphId
    private Long id;

    public Names getStart() {
        return start;
    }

    public void setStart(Names start) {
        this.start = start;
    }

    public FirstName getEnd() {
        return end;
    }

    public void setEnd(FirstName end) {
        this.end = end;
    }

    public Of() {
    }

    @StartNode

    private Names start;

    @EndNode
    private FirstName end;

    public Of(Names start, FirstName end) {
        this.start = start;
        this.end = end;
    }


}
