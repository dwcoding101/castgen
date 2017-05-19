package org.gen.cast.name;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = OfSurnames.TYPE)
public class OfSurnames {
    public static final String TYPE = "OF_SURNAMES";

    @GraphId
    private Long id;

    public Names getStart() {
        return start;
    }

    public void setStart(Names start) {
        this.start = start;
    }

    public Surnames getEnd() {
        return end;
    }

    public void setEnd(Surnames end) {
        this.end = end;
    }

    public OfSurnames() {
    }

    @StartNode

    private Names start;

    @EndNode
    private Surnames end;

    public OfSurnames(Names start, Surnames end) {
        this.start = start;
        this.end = end;
    }


}
