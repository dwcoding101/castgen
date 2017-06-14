package org.gen.cast.relations;

import org.gen.service.Id;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Daniel on 05/06/2017.
 */
@NodeEntity(label = RelationshipNode.LABEL)
public class RelationshipNode implements Id{
    public static final String LABEL ="RELATIONSHIP_NODE";
    @GraphId()
    private Long id;

    @Index(unique = true)
    @Convert(UuidStringConverter.class)
    private UUID uuid;

    public Long getId() {
        return id;
    }

    @Property(name = "current")
    private boolean current;

    @Property(name = "date")
    private Date date;

    @Property(name = "meanStrength")
    private Long meanStrength;

    @org.neo4j.ogm.annotation.Relationship(type = Relationship.TYPE, direction = org.neo4j.ogm.annotation.Relationship.INCOMING)
    List<Relationship> relationship;

    public RelationshipNode() {
    }

    public RelationshipNode(UUID aUuid, UUID bUuid, long mean, long aStrength, long bStrength,Date date) {
        this.uuid = UUID.randomUUID();
        this.date = date;

        RelationsImpl relationsImpl = new RelationsImpl();

        Relations relationsa = relationsImpl.find(aUuid, 1);
        Relations relationsb = relationsImpl.find(bUuid, 1);

        relationship = new ArrayList<>();
        this.relationship.add(new Relationship(this,relationsa, aStrength ));
        this.relationship.add(new Relationship(this,relationsb, bStrength ));

        this.meanStrength = mean + (aStrength + bStrength)/2;
        if(this.meanStrength >= 100) {
            this.meanStrength = 100L;
        }
        if(this.meanStrength <= 1) {
            this.meanStrength = 1L;
        }

        this.current = true;
    }
}
