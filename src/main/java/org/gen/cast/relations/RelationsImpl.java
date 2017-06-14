package org.gen.cast.relations;

import com.google.common.collect.Lists;
import org.gen.service.GenericService;
import org.gen.service.Id;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Daniel on 27/05/2017.
 */
public class RelationsImpl extends GenericService<Relations> {

    @Override
    public Relations find(String uuid) {

        //Iterable<Relations> tIterable = session.query(getEntityType(),"Match p=(:RELATIONS)-[:"+Relationship.TYPE+"*0..1]->(a:RELATIONS{uuid:'"+uuid+"'})-[:"+Relationship.TYPE+"*0..1]->(:RELATIONS) RETURN p", Collections.EMPTY_MAP);


        //List aIterable = Lists.newArrayList(tIterable);



        Relations rel = session.load(getEntityType(), UUID.fromString(uuid),2);

        return (Relations) rel; // aIterable.get(0);

    }




    @Override
    public void update(Id<Relations> object) {
        System.out.println("1");
        Relations relations = (Relations) object;
        System.out.println("relations: " + relations.getUuid());
        System.out.println("relationships incoming size: " + relations.getRelationshipListIncoming().size());
        System.out.println("relationships outgoing size: " + relations.getRelationshipListOutgoing().size());

     //   Iterable<Relations> tIterable = session.query(getEntityType(),"Match p=(:RELATIONS)-[:"+Relationship.TYPE+"*0..1]->(a:RELATIONS{uuid:'"+relations.getUuid()+"'})-[:"+Relationship.TYPE+"*0..1]->(:RELATIONS) RETURN p", Collections.EMPTY_MAP);


//        List aIterable = Lists.newArrayList(tIterable);


            session.save(relations, 1);

    }

    @Override
    public Class<Relations> getEntityType() {
        return Relations.class;
    }

    public void harmoniseRelations(){
        String CypherQuerry = "MATCH (a:RELATIONS)-[b:RELATIONSHIP]->(c:RELATIONS),(a)<-[d:RELATIONSHIP]-(c) SET b.strength= (toInteger(b.strength) + toInteger(d.strength))/2, d.strength = (toInteger(b.strength) + toInteger(d.strength))/2 RETURN b.strength, d.strength";

        Result results = session.query(CypherQuerry, Collections.emptyMap());
    }
}
