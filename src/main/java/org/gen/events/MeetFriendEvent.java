package org.gen.events;

import org.gen.cast.relations.Relations;
import org.gen.cast.relations.RelationsImpl;
import org.gen.service.Id;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Daniel on 05/06/2017.
 */
@NodeEntity(label = MeetFriendEvent.LABEL )
public class MeetFriendEvent extends Event implements Id {
    public static final String LABEL = "MEET_FRIEND_EVENT";

    @Relationship(type = EventReaction.TYPE, direction = Relationship.INCOMING)
    List<EventReaction> reactions;

    @Property
    Date date;

    @Index(unique = true)
    @Convert(UuidStringConverter.class)
    UUID uuid;

    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }

    public MeetFriendEvent() {
    }

    public MeetFriendEvent(String eventsUUID1, String eventsUUID2, LocalDate date) {
        this.uuid = UUID.randomUUID();


        Date date2 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.date = date2;

        EventsImpl eventsImpl = new EventsImpl();
        Events rels1 = eventsImpl.find(eventsUUID1,1);
        Events rels2 = eventsImpl.find(eventsUUID2,1);

        reactions = new ArrayList<>();
        reactions.add(new EventReaction(rels1,10,23,this));
        reactions.add(new EventReaction(rels2,10,23,this));

    }

    public MeetFriendEvent(String eventsUUID1, String eventsUUID2,int strength1, int strength2, LocalDate date) {
        this.uuid = UUID.randomUUID();


        Date date2 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.date = date2;

        EventsImpl eventsImpl = new EventsImpl();
        Events rels1 = eventsImpl.find(eventsUUID1,1);
        Events rels2 = eventsImpl.find(eventsUUID2,1);

        reactions = new ArrayList<>();
        reactions.add(new EventReaction(rels1,0,strength1,this));
        reactions.add(new EventReaction(rels2,0,strength2,this));

    }


}
