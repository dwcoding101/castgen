package org.gen.cast.name;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = OfSurnamePart1.TYPE)
public class OfSurnamePart1 {
    public static final String TYPE = "OF_SURNAME_PART_1";

    @GraphId
    private Long id;

    public Surnames getStart() {
        return start;
    }

    public void setStart(Surnames start) {
        this.start = start;
    }

    public SurnamePart1 getEnd() {
        return end;
    }

    public void setEnd(SurnamePart1 end) {
        this.end = end;
    }

    public OfSurnamePart1() {
    }

    @StartNode

    private Surnames start;

    @EndNode
    private SurnamePart1 end;

    public OfSurnamePart1(Surnames start, SurnamePart1 end) {
        this.start = start;
        this.end = end;
    }


}
