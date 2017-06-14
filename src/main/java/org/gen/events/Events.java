package org.gen.events;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.time.StopWatch;
import org.gen.cast.CastMember;
import org.gen.cast.CastMemberImpl;
import org.gen.cast.race.Race;
import org.gen.dates.Calendar;
import org.gen.dates.CalendarImpl;
import org.gen.dates.DateTime;
import org.gen.dates.DateTimeImpl;
import org.gen.factory.Neo4jSessionFactory;
import org.gen.random.Dice;
import org.gen.service.Id;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;
import org.neo4j.ogm.typeconversion.UuidStringConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;



/**
 * Created by Daniel on 15/05/2017.
 */
@NodeEntity(label=Events.LABEL)
public class Events implements Id{
    public static final String LABEL="EVENTS";

    @GraphId
    private Long id;

    @Index(unique = true)
    @Convert(UuidStringConverter.class)
    private UUID uuid = UUID.randomUUID();

    @Transient
    Calendar calendar = Calendar.getIntance();

    public UUID getUuid() {
        return uuid;
    }

    @Relationship(type = EventReaction.TYPE)
    List<EventReaction> events = new ArrayList<>();




    public void addEventYearsDaysInThePast(Event event, int years, int days) {

       event.date = calendar.yearsDaysInThePast(years, days);

       events.add(new EventReaction(this, event));
      // System.out.println(event.date.toString());
    }

    public void addEventYearsDaysInThePast(Event event, int reaction,int interaction, int years, int days) {

        event.date = calendar.yearsDaysInThePast(years, days);

        events.add(new EventReaction(this, reaction, interaction, event));
        // System.out.println(event.date.toString());
    }

    public void addNpcLife(int years, int days) {

        DateTime conceptionDate = calendar.yearsDaysInThePast(years, days + 273);

        ConceptionEvent conceptionEvent = new ConceptionEvent(conceptionDate,"Human");
        DateTime dob = calendar.yearsDaysInThePast(years,days);
        BirthEvent birthEvent = new BirthEvent(dob);
        events.add(new EventReaction(this, conceptionEvent));
        events.add(new EventReaction(this, birthEvent));


    }

    public void addMeeting(List<CastMember> attendee,int years, int days){
        Stopwatch sw = Stopwatch.createUnstarted();

        CastMemberImpl castMemberImpl = new CastMemberImpl();

        MeetingEvent meetingEvent = new MeetingEvent();

        Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

        Transaction tx = session.beginTransaction();

        for (CastMember cast: attendee) {
            sw.start();
            System.out.println("add event to " + cast.getName());

            CastMember castn = castMemberImpl.find(cast.getUuid().toString());
          //  int reaction = (int) Dice.roll("1D3");
            int interaction = (int) Dice.roll("1D3");

            int reaction = (int) Math.floor((double)cast.getStat("Fel")/10);
            if(!cast.testStat("Fel","1D100")){
                interaction  = -1 * interaction;
            }

            castn.getLifeEvents().addEventYearsDaysInThePast(meetingEvent,reaction,interaction , years, days);
            castMemberImpl.update(castn);


            System.out.println("add meeting : " + sw);
            sw.reset();
        }
        tx.commit();
        tx.close();
      //  sw.stop();

    }



    public DateTime getDateOfBirth() {

        DateTime dob = null;

        for (EventReaction eventRection: events) {
            Event event = eventRection.getEnd();
            if(event instanceof BirthEvent ){
                BirthEvent birthEvent = (BirthEvent)event;
                dob = birthEvent.date;
                dob.processDateTime();
            }
        }

        return dob;
    }

    public DateTime getDateOfDeath() {

        DateTime dod = null;

        for (EventReaction eventRection: events) {
            Event event = eventRection.getEnd();
            if(event instanceof DeathEvent ){
                DeathEvent birthEvent = (DeathEvent)event;
                dod = birthEvent.date;
                dod.processDateTime();
            }
        }

        return dod;
    }


    @Override
    public Long getId() {
        return id;
    }
}
