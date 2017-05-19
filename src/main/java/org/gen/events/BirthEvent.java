package org.gen.events;

import org.gen.dates.DateTime;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 15/05/2017.
 */
@NodeEntity(label = BirthEvent.LABEL)
public class BirthEvent extends Event {
    public static final String LABEL = "BIRTH_EVENT";
    public static final String EVENT_TYPE = "BIRTH";

    public String race;

    //NPC Mother and Father must be add when the class has been


    public BirthEvent() {

    }

    public BirthEvent(DateTime day) {
        this.type = EVENT_TYPE;
        this.date = day;
    }




}
