package org.gen.cast.name;

import org.gen.cast.gender.Sex;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Daniel on 08/05/2017.
 */
@RelationshipEntity(type=Called.TYPE)
public class Called {
    public static final String TYPE = "CALLED";

    @GraphId
    private Long id;

    @StartNode
    private Sex start;

    @EndNode
    private Names end;

    public Called(Sex start, Names end) {
        this.start = start;
        this.end = end;
    }
}
