package org.gen.events;

import org.gen.cast.relations.RelationshipNode;
import org.gen.cast.relations.RelationshipNodeImpl;
import org.gen.factory.Neo4jSessionFactory;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Daniel on 05/06/2017.
 */
public class EventProcessor {

    public EventProcessor() {
    }

    public void addEvent(Event event) {
        // What Type of event

        if(event instanceof MeetFriendEvent) {
            // add event
            MeetFriendEvent meetFriendEvent = (MeetFriendEvent) event;
            MeetFriendEventImpl meetFriendEventImpl = new MeetFriendEventImpl();
            meetFriendEventImpl.update(meetFriendEvent);

            // UUID OF EVENTS
            UUID eventsUuid1 = meetFriendEvent.reactions.get(0).getStart().getUuid();
            UUID eventsUuid2 = meetFriendEvent.reactions.get(1).getStart().getUuid();
            Long eventInteraction1 = meetFriendEvent.reactions.get(0).getInteraction();
            Long eventInteraction2 = meetFriendEvent.reactions.get(1).getInteraction();
            Date date = meetFriendEvent.date;

            // Find UUID of RELATIONS from UUID EVENTS
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

            UUID relationsUuid1 = UUID.fromString(session.queryForObject(String.class,"MATCH (a:EVENTS)<--(b:NPC)-->(c:RELATIONS) WHERE a.uuid='"+eventsUuid1.toString()+"' RETURN c.uuid AS UUID", Collections.emptyMap()).toString());
            UUID relationsUuid2 = UUID.fromString(session.queryForObject(String.class,"MATCH (a:EVENTS)<--(b:NPC)-->(c:RELATIONS) WHERE a.uuid='"+eventsUuid2.toString()+"' RETURN c.uuid AS UUID", Collections.emptyMap()).toString());

            // all passed RelationshipNodes set current to false
            String q = "MATCH (a:RELATIONS)-[:RELATIONSHIP]->(b:RELATIONSHIP_NODE)<-[:RELATIONSHIP]-(c:RELATIONS) " +
                    "WHERE a.uuid='"+relationsUuid1.toString()+"' AND c.uuid='"+relationsUuid2.toString()+"' AND b.current=true " +
                    "SET b.current=false " +
                    "with b.meanStrength as mean, b.date as date " +
                    "ORDER BY date DESCENDING " +
                    "RETURN mean, date";
            Result results = session.query(q, Collections.emptyMap());

            long meanStrength = 0;
            for (Map<String,Object> result :results) {
                meanStrength = (long) result.get("mean");
            }

            // and new RelationshipNode
            RelationshipNodeImpl relationshipNodeImpl = new RelationshipNodeImpl();
            RelationshipNode relationshipNode = new RelationshipNode(relationsUuid1,relationsUuid2,meanStrength,eventInteraction1,eventInteraction2,date);
            relationshipNodeImpl.update(relationshipNode);


        }
    }
}
