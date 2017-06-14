package org.gen.events;

import org.gen.cast.stat.Stat;
import org.gen.cast.stat.Stats;
import org.neo4j.ogm.annotation.*;

import java.util.UUID;

/**
 * Created by Daniel on 25/05/2017.
 */

@RelationshipEntity(type = EventReaction.TYPE)
public class EventReaction {
    public static final String TYPE = "EVENT_REACTION";

    @GraphId
    private Long id;

    @Property
    private String uuid;

    @StartNode
    private Events start;

    @Property
    private Long reaction;

    @Property
    private Long interaction;

    @EndNode
    private Event end;

    public EventReaction(Events start, Event end) {

        this.uuid = UUID.randomUUID().toString();
        this.start = start;
        this.end = end;
    }

    public Events getStart() {
        return start;
    }

    public EventReaction(Events start, int rection, int interaction, Event end) {
        this.reaction = (long) rection;
        this.interaction = (long) interaction;
        this.uuid = UUID.randomUUID().toString();
        this.start = start;
        this.end = end;
    }

    public EventReaction() {
    }

    public Event getEnd() {
        return end;
    }

    public Long getReaction() {
        return reaction;
    }

    public Long getInteraction() {
        return interaction;
    }
}
