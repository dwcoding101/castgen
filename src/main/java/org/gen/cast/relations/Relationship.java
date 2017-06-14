package org.gen.cast.relations;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.UUID;

/**
 * Created by Daniel on 25/05/2017.
 */
@RelationshipEntity(type= Relationship.TYPE)
public class Relationship {

    public static final String TYPE = "RELATIONSHIP";


    @Index(unique = true,primary = true)
    @Convert(UuidStringConverter.class)
    private UUID uuid;

    @Property
    private Long strength;

    @GraphId
    private Long id;

    public Long getStrength() {
        return strength;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    @EndNode
    RelationshipNode start;

    @StartNode
    Relations end;

    public Relationship() {
    }

    public Relationship (RelationshipNode start, Relations end) {
        this.start = start;
        this.end = end;
        this.uuid = UUID.randomUUID();
    }

    public Relationship (RelationshipNode start, Relations end, long strength) {
        this.start = start;
        this.end = end;
        this.strength = strength;
        this.uuid = UUID.randomUUID();
    }
}
