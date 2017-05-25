package org.gen.cast;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.StopWatch;
import org.gen.cast.gender.Genders;
import org.gen.cast.gender.Sex;
import org.gen.cast.name.Names;
import org.gen.cast.name.Surnames;
import org.gen.cast.race.Race;
import org.gen.cast.race.RaceImpl;
import org.gen.cast.stat.Stat;
import org.gen.cast.stat.Stats;
import org.gen.dates.*;
import org.gen.factory.Neo4jSessionFactory;
import org.gen.random.Dice;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 04/05/2017.
 */
public class TestCastMember {



    public static void main(String[] args) {

//        SessionFactory sessionFactory = new SessionFactory("org.gen");
//        final Session session = sessionFactory.openSession();
//       // Transaction tx = session.beginTransaction();
//


        boolean createOrLoad =true;
        boolean setupDataBaseForNPC= true;
        boolean createcast =true;
        boolean eventGenerator = true;
        boolean load_via_uuid = false;

        CalendarImpl calendarImpl = new CalendarImpl();
        if(createOrLoad) {

            Calendar calendar = Calendar.getIntance();

            calendar.days.addDayOfTheWeek(new Day(0L, "Wellentag"));
            calendar.days.addDayOfTheWeek(new Day(1L, "Aubentag"));
            calendar.days.addDayOfTheWeek(new Day(2L, "Marktag"));
            calendar.days.addDayOfTheWeek(new Day(3L, "Backertag"));
            calendar.days.addDayOfTheWeek(new Day(4L, "Bezahltag"));
            calendar.days.addDayOfTheWeek(new Day(5L, "Konistag"));
            calendar.days.addDayOfTheWeek(new Day(6L, "Angestag"));
            calendar.days.addDayOfTheWeek(new Day(7L, "Festag"));

            calendar.addMonth(new Month(0L, "Nachexen", 32L));
            calendar.addMonth(new Month(1L, "Jahrdrung", 33L));
            calendar.addMonth(new Month(2L, "Pflugzeit", 33L));
            calendar.addMonth(new Month(3L, "Sigmarzeit", 33L));
            calendar.addMonth(new Month(4L, "Sommerzeit", 33L));
            calendar.addMonth(new Month(5L, "Vorgeheim", 33L));
            calendar.addMonth(new Month(6L, "Nachgeheim", 32L));
            calendar.addMonth(new Month(7L, "Erntezeit", 33L));
            calendar.addMonth(new Month(8L, "Brauzeit", 33L));
            calendar.addMonth(new Month(9L, "Kaldezeit", 33L));
            calendar.addMonth(new Month(10L, "Ulriczeit", 33L));
            calendar.addMonth(new Month(11L, "Vorhexen", 33L));

            calendarImpl.createOrUpdate(calendar);

        }


        if (setupDataBaseForNPC) {
            RaceImpl raceImpl = new RaceImpl();

            Names malenames = new Names();

            malenames.loadFirstNames("human_male_first_names.txt");


            Sex male = new Sex("male");

            male.addNames(malenames);


            Surnames surnames = new Surnames();

            surnames.loadSurnamesPart1("human_surnames_part_1.txt");

            surnames.loadSurnamesPart2("human_surnames_part_2.txt");

            malenames.addSurnames(surnames);

            Race race = new Race();
            Genders gender = new Genders();

            Names femalenames = new Names();

            femalenames.loadFirstNames("human_female_first_names.txt");
            femalenames.addSurnames(surnames);

            Sex female = new Sex("female");
            female.addNames(femalenames);

            gender.addSex(male);
            gender.addSex(female);

            race.setName("Human");
            race.addWith(gender);

            Stat ws = new Stat("WS", 1, "2D10+20");
            Stat bs = new Stat("BS", 2, "2D10+20");
            Stat s = new Stat("S", 3, "2D10+20");
            Stat t = new Stat("T", 4, "2D10+20");
            Stat ag = new Stat("Ag", 5, "2D10+20");
            Stat Int = new Stat("Int", 6, "2D10+20");
            Stat WP = new Stat("WP", 7, "2D10+20");
            Stat Fel = new Stat("Fel", 8, "2D10+20");
            Stat a = new Stat("A", 9, "1");
            Stat w = new Stat("W", 10, "RandomTableSelect('D10','1-3:10','4-6:11','7-9:12','10:13')");
            Stat sb = new Stat("SB", 11, "DerivedFrom('S/10')");
            Stat tb = new Stat("TB", 12, "DerivedFrom('T/10')");
            Stat m = new Stat("M", 13, "4");
            Stat mag = new Stat("Mag", 14, "0");
            Stat ip = new Stat("IP", 15, "0");


            Stats stats = new Stats();

            stats.addStat(ws);
            stats.addStat(bs);
            stats.addStat(s);
            stats.addStat(t);
            stats.addStat(ag);
            stats.addStat(Int);
            stats.addStat(WP);
            stats.addStat(Fel);
            stats.addStat(a);
            stats.addStat(sb);
            stats.addStat(tb);
            stats.addStat(w);
            stats.addStat(m);
            stats.addStat(mag);
            stats.addStat(ip);

            race.addWith(stats);

            raceImpl.createOrUpdate(race);
        }

        if (createcast) {

                int number_of_npc = 100;

                for (int i = 0; i < number_of_npc; i++) {
                    CastMember castMember = new CastMember("Human");
                    CastMemberImpl castMember1 = new CastMemberImpl();
                    castMember1.createOrUpdate(castMember);
                    System.out.println(castMember.toFormatedString());
                }
            }



        // ramdon cast picker
        if(eventGenerator){
            int numberOfEvents = 150;


            // Load all NPC
            Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
            Iterable<CastMember> npcIterator = session.query(CastMember.class,"MATCH n=(r:NPC)-[*1..5]->() return n as NPC", Collections.EMPTY_MAP );

            Calendar calendar = Calendar.getIntance();

            //convert to List
            List<CastMember> npcList = Lists.newArrayList(npcIterator);

            Stopwatch sw = Stopwatch.createUnstarted();

            for (int i = 0; i < numberOfEvents; i++){
                sw.start();
                int numberInMeeting = (int) Dice.roll("1D10+1");
                int years = (int) Dice.roll("1D6");
                int days = (int) Dice.roll("1D394");
                DateTime eventDateTime = calendar.yearsDaysInThePast(years,days);
                List<CastMember> meetingMembers = new ArrayList<>();
                int size =  npcList.size() -1;
                for(int member=0; member < numberInMeeting; member++ ){
                    int npcNumber = (int) Dice.roll("1D"+size);
                    CastMember npc = npcList.get(npcNumber);
                    if(npc.isAlive(eventDateTime)) {
                        // make sure that the member is not already in the list
                        if(meetingMembers.contains(npc)) {
                            member--;
                        }
                        meetingMembers.add(npc);
                    }
                    else {
                        member--;
                    }
                }
                sw.stop();
                System.out.println("Stopwatch before: " + sw);
                sw.reset();
                sw.start();

                CastMember castMember = meetingMembers.get(0);
                castMember.getLifeEvents().addMeeting(meetingMembers,years,days);
                sw.stop();
                System.out.println("Stopwatch after: " + sw);
            }
            //



        }

        if (load_via_uuid) {
            CastMemberImpl castMemberl = new CastMemberImpl();

            CastMember castMember = castMemberl.find("73492fd7-d8a4-4c71-9b07-a356ebebb2c2");

            System.out.println(castMember.getName());
        }



    }
}
