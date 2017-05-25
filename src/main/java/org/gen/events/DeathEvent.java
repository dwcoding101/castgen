package org.gen.events;

import org.gen.dates.DateTime;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 22/05/2017.
 */
@NodeEntity(label = DeathEvent.LABEL)
public class DeathEvent extends Event {
    public static final String LABEL = "DEATH_EVENT";
    public static final String EVENT_TYPE = "DEATH";

    public String race;

    //NPC Mother and Father must be add when the class has been


    public DeathEvent() {

    }

    public DeathEvent(DateTime day) {
        this.type = EVENT_TYPE;
        this.date = day;
    }
}
