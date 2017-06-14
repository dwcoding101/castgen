package org.gen.cast.relations;

import com.google.common.base.Stopwatch;
import org.gen.factory.Neo4jSessionFactory;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


/**
 * Created by Daniel on 29/05/2017.
 */

public class ProcessAllRelations {

    protected Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();


    public void harmonize() {
        RelationsImpl relations = new RelationsImpl();
        relations.harmoniseRelations();
    }

    public void processAll() {
        Result results = session.query("MATCH (a:NPC)-->(:EVENTS)-[b:EVENT_REACTION]->(:MEETING_EVENT)<-[c:EVENT_REACTION]-(:EVENTS)<--(d:NPC), (a)-->(g:RELATIONS),(d)-->(h:RELATIONS) RETURN g.uuid, b.reaction, c.interaction, h.uuid", Collections.emptyMap());

       // Transaction tx = this.session.beginTransaction();
        RelationsImpl relations = new RelationsImpl();
        int i = 1;

        for (Map<String,Object> result :results) {


            String gUuid = result.get("g.uuid").toString();
            String hUuid = result.get("h.uuid").toString();
            long reaction = (long) result.get("b.reaction");
            long interaction = (long) result.get("c.interaction");

            System.out.println("a: " +gUuid+ " b: " +hUuid);
            Relations rel = relations.find(gUuid);
            System.out.println("a: "+rel.getUuid());
            long strength = reaction + interaction;


            int exists = 0;
            int positons = 1;
            int temp = 1;
            // check if relationship already exists
            if(rel.getRelationshipListIncoming() == null) {
                rel.setRelationshipListIncoming(new ArrayList<>());
            }

            if (rel.getRelationshipListOutgoing() != null) {
                for (Relationship rela : rel.getRelationshipListOutgoing()) {
                    if (rela.end.getUuid().equals(hUuid)) {
                        exists++;
                        positons = temp;
                    }
                    temp++;
                }
            }
            else {
                rel.setRelationshipListOutgoing(new ArrayList<>()) ;
            }


            if( exists > 0){
                // the relationship already exist
                //  get and alter relationship
                Relationship rela = rel.getRelationshipListOutgoing().get(positons-1);

                long str = rela.getStrength();
                rela.setStrength(str + strength);

                Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

                session.save(rela,1);
            }
            else {
                //load Npc relation
                RelationsImpl relImp = new RelationsImpl();
                Relations relNpc =relImp.find(hUuid);

          //      relNpc.getRelationshipListIncoming().add(new Relationship(relNpc, rel, strength));

          //      rel.getRelationshipListOutgoing().add(new Relationship(rel, relNpc,strength));
                relImp.update(relNpc);
                relImp.update(rel);




            }
            System.out.println("a: " +gUuid+ " b: " +hUuid);

       //     relations.update(rel);

            //System.out.println("a: " +aUuid+ " b: " +bUuid);
            System.out.println("Ration: " + i++);

            if(i == 600){
                System.out.println("got here");
            }

        }


    }


    public void processUnactioned() {
        Result results = session.query("MATCH (a:NPC)-->(:EVENTS)-[b:EVENT_REACTION]->(x:MEETING_EVENT)<-[c:EVENT_REACTION]-(:EVENTS)<--(d:NPC), (a)-->(g:RELATIONS),(d)-->(h:RELATIONS) WHERE x.eventActioned = false RETURN g.uuid, b.reaction, c.interaction, h.uuid", Collections.emptyMap());

        // Transaction tx = this.session.beginTransaction();
        RelationsImpl relations = new RelationsImpl();
        int i = 1;

        for (Map<String,Object> result :results) {


            String gUuid = result.get("g.uuid").toString();
            String hUuid = result.get("h.uuid").toString();
            long reaction = (long) result.get("b.reaction");
            long interaction = (long) result.get("c.interaction");

            System.out.println("a: " +gUuid+ " b: " +hUuid);
            Relations rel = relations.find(gUuid);
            System.out.println("a: "+rel.getUuid());
            long strength = reaction + interaction;


            int exists = 0;
            int positons = 1;
            int temp = 1;
            // check if relationship already exists
            if(rel.getRelationshipListIncoming() == null) {
                rel.setRelationshipListIncoming(new ArrayList<>());
            }

            if (rel.getRelationshipListOutgoing() != null) {
                for (Relationship rela : rel.getRelationshipListOutgoing()) {
                    if (rela.end.getUuid().equals(hUuid)) {
                        exists++;
                        positons = temp;
                    }
                    temp++;
                }
            }
            else {
                rel.setRelationshipListOutgoing(new ArrayList<>()) ;
            }


            if( exists > 0){
                // the relationship already exist
                //  get and alter relationship
                Relationship rela = rel.getRelationshipListOutgoing().get(positons-1);

                long str = rela.getStrength();
                rela.setStrength(str + strength);

                Session session =  Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

                session.save(rela,1);
            }
            else {
                //load Npc relation
                RelationsImpl relImp = new RelationsImpl();
                Relations relNpc =relImp.find(hUuid);

        //        relNpc.getRelationshipListIncoming().add(new Relationship(relNpc, rel, strength));

        //        rel.getRelationshipListOutgoing().add(new Relationship(rel, relNpc,strength));
                relImp.update(relNpc);
                relImp.update(rel);




            }
            System.out.println("a: " +gUuid+ " b: " +hUuid);

            //     relations.update(rel);

            //System.out.println("a: " +aUuid+ " b: " +bUuid);
            System.out.println("Ration: " + i++);

            if(i == 600){
                System.out.println("got here");
            }

        }


    }


}
