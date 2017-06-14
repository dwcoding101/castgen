package org.gen.cast.relations;

import org.gen.service.Id;
import org.gen.service.UUIDConverter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;


import java.util.*;

/**
 * Created by Daniel on 25/05/2017.
 */

@NodeEntity(label = Relations.NAME)
public class Relations implements Id{



    public static final String NAME = "RELATIONS";

    @Index(unique = true,primary = true)
    @Convert(UuidStringConverter.class)
    public UUID uuid ;

    @GraphId
    private Long id;

    public Relations() {
    }

    public void createUuid(){
         this.uuid = UUID.randomUUID();
    }

    public List<Relationship> getRelationshipListOutgoing() {
        if(relationshipListOutgoing == null) {
            this.relationshipListOutgoing = new ArrayList<>();
        }
        return this.relationshipListOutgoing;
    }

    public List<Relationship> getRelationshipListIncoming() {
        if(relationshipListIncoming == null) {
            this.relationshipListIncoming = new ArrayList<>();
        }
        return this.relationshipListIncoming;
    }

    @org.neo4j.ogm.annotation.Relationship(type= Relationship.TYPE,direction = org.neo4j.ogm.annotation.Relationship.OUTGOING)
    private List<Relationship> relationshipListOutgoing;

    @org.neo4j.ogm.annotation.Relationship(type= Relationship.TYPE,direction = org.neo4j.ogm.annotation.Relationship.INCOMING)
    private List<Relationship> relationshipListIncoming;

    public String getUuid() {
        return uuid.toString();
    }

    public Long getId() {
        return id;
    }

    public void setRelationshipListOutgoing(List<Relationship> relationshipListOutgoing) {
        this.relationshipListOutgoing = relationshipListOutgoing;
    }

    public void setRelationshipListIncoming(List<Relationship> relationshipListIncoming) {
        this.relationshipListIncoming = relationshipListIncoming;
    }

    public void addedRelationship(String uuidOfNpcRelaction, long strength) {




    }

}
