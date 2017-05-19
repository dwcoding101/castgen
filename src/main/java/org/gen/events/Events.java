package org.gen.events;

import org.gen.cast.race.Race;
import org.gen.dates.Calendar;
import org.gen.dates.CalendarImpl;
import org.gen.dates.DateTime;
import org.gen.dates.DateTimeImpl;
import org.gen.factory.Neo4jSessionFactory;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 15/05/2017.
 */
@NodeEntity(label=Events.LABEL)
public class Events {
    public static final String LABEL="EVENTS";

    @Transient
    Calendar calendar;

    private Long id;

    List<Event> events = new ArrayList<>();

    public void loadCalendar(){
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
        Iterable<Calendar> calendarNodes = session.query(Calendar.class,"MATCH n=(r:CALENDAR)-[*1..5]->() return n as CALENDAR", Collections.EMPTY_MAP );

        calendarNodes.forEach((calendarNode)->{
            calendar = calendarNode;
        });



    }

    public void addEventYearsDaysInThePast(Event event, int years, int days) {
       event.date = calendar.yearsDaysInThePast(years, days);

       events.add(event);
       System.out.println(event.date.toString());
    }

    public void addNpcLife(int years, int days) {

        DateTime conceptionDate = calendar.yearsDaysInThePast(years, days + 273);

        ConceptionEvent conceptionEvent = new ConceptionEvent(conceptionDate,"Human");
        DateTime dob = calendar.yearsDaysInThePast(years,days);
        BirthEvent birthEvent = new BirthEvent(dob);
        events.add(conceptionEvent);
        events.add(birthEvent);


    }


}
