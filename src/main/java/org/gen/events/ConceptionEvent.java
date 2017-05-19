package org.gen.events;

import org.gen.dates.DateTime;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 15/05/2017.
 */
@NodeEntity(label = ConceptionEvent.LABEL)
public class ConceptionEvent extends Event {
    public static final String LABEL = "CONCEPTION_EVENT";
    public static final String EVENT_TYPE = "CONCEPTION";

    public String race;

    //NPC Mother and Father must be add when the class has been


    public ConceptionEvent() {

    }

    public ConceptionEvent(DateTime day) {
        this.type = EVENT_TYPE;
        this.date = day;
    }

    public ConceptionEvent(DateTime day, String race) {
        this.type = EVENT_TYPE;
        this.date = day;
        this.race = race;
    }



}
