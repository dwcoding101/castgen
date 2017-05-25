package org.gen.events;

import org.gen.cast.CastMember;
import org.gen.dates.DateTime;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * Created by Daniel on 19/05/2017.
 */
@NodeEntity(label = MeetingEvent.LABEL)
public class MeetingEvent extends Event {

    public static final String SOCIAL = "SOCIAL";
    public static final String FIGHT = "FIGHT";
    public static final String PARTY = "PARTY";



    public static final String LABEL = "MEETING_EVENT";
    public static final String EVENT_TYPE = "MEETING";

    public String meetingType;

    public MeetingEvent() {

    }

    public MeetingEvent(DateTime day) {
        this.type = EVENT_TYPE;
        this.date = day;
    }
}
