package org.gen.cast;

import org.gen.cast.gender.Genders;
import org.gen.cast.gender.Sex;
import org.gen.cast.name.Names;
import org.gen.cast.name.Surnames;
import org.gen.cast.race.Race;
import org.gen.cast.race.RaceImpl;
import org.gen.cast.stat.Stat;
import org.gen.cast.stat.Stats;
import org.gen.dates.CalendarImpl;

/**
 * Created by Daniel on 04/05/2017.
 */
public class TestCastMember {

    public static void main(String[] args) {

//        SessionFactory sessionFactory = new SessionFactory("org.gen");
//        final Session session = sessionFactory.openSession();
//       // Transaction tx = session.beginTransaction();
//

        boolean setupDataBaseForNPC= false;
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
        else {
            CastMember castMember = new CastMember("Human");

            CastMemberImpl castMember1 = new CastMemberImpl();

            castMember1.createOrUpdate(castMember);

            System.out.println(castMember.toFormatedString());
        }
//        Race testRace = session.load(Race.class,96);

  //      System.out.println("Race name:= " + testRace.getName());


       // long current = race.getId();

//        try {
//                RaceImpl raceImpl = new RaceImpl();
//              //  raceImpl.createOrUpdate(race);
//
//                Iterable<Race> iterableRace = raceImpl.findAll();
//
//                iterableRace.forEach((raceIt)->{
//                System.out.println("Races:= " + raceIt.getName());
//
//                Sex sex =raceIt.getGender().pickRandomSex();
//              //  System.out.println("Genders of race:= " + raceIt.nowManyGenders());
//              //  System.out.println("Genders of race:= " + raceIt.getGender().howManySexes());
//                System.out.println("Sex:= " + sex.getType());
//                System.out.println("firstName:= " + sex.getNames().pickRandomFirstName().getName());
//                System.out.println("Surname:= " + raceIt.getGender().pickRandomSex().getNames().getSurnames().pickRandomSurname());
//            });
//
////          raceImpl.createOrUpdate(race);
////            Race it= raceImpl.find(Long.parseLong("10657"));
////
////
////
////            System.out.println("Races:= " + it.getName());
////            System.out.println("firstName:= " + it.getGender().getSex("male").getNames().pickRandomFirstName().getName());
////            System.out.println("firstName:= " + it.getGender().getSex("male").getNames().getSurnames().pickRandomSurname());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        //tx.commit();
//
//
//
//       // tx.close();


    }
}
