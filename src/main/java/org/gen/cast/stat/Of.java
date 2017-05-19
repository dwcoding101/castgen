package org.gen.cast.stat;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 04/05/2017.
 */

@RelationshipEntity(type = Of.TYPE)
public class Of {
    public static final String TYPE = "OF_STAT";

    @GraphId
    private Long id;

    @StartNode
    private Stats start;

    @EndNode
    private Stat end;

    public Of(Stats start, Stat end) {
        this.start = start;
        this.end = end;
    }

    public Of() {
    }

    public Stat getEnd() {
        return end;
    }
}
