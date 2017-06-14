package org.gen.events;

import org.gen.dates.DateTime;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.UUID;

/**
 * Created by Daniel on 15/05/2017.
 */
@NodeEntity(label = Event.LABEL)
public class Event {
    public static final String LABEL = "EVENT";
    private Long id;
    DateTime date;
    Boolean eventActioned;
    String type;
    @Index(unique = true)
    String uuid = UUID.randomUUID().toString();


}
