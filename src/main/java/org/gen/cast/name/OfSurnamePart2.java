package org.gen.cast.name;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = OfSurnamePart2.TYPE)
public class OfSurnamePart2 {
    public static final String TYPE = "OF_SURNAME_PART_2";

    @GraphId
    private Long id;

    public Surnames getStart() {
        return start;
    }

    public void setStart(Surnames start) {
        this.start = start;
    }

    public SurnamePart2 getEnd() {
        return end;
    }

    public void setEnd(SurnamePart2 end) {
        this.end = end;
    }

    public OfSurnamePart2() {
    }

    @StartNode

    private Surnames start;

    @EndNode
    private SurnamePart2 end;

    public OfSurnamePart2(Surnames start, SurnamePart2 end) {
        this.start = start;
        this.end = end;
    }


}
