package org.gen.cast;

import org.gen.cast.gender.Sex;
import org.gen.cast.race.Race;
import org.gen.cast.stat.Of;
import org.gen.cast.stat.Stat;
import org.gen.cast.stat.Stats;
import org.gen.events.Events;
import org.gen.factory.Neo4jSessionFactory;
import org.gen.random.Dice;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Transient;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 04/05/2017.
 */

@NodeEntity(label = CastMember.TYPE)
public class CastMember {
    public static final String TYPE="NPC";

    private String firstName;
    private String familyName;
    private String race;
    @Transient
    private Race raceNodes;
    private String gender;
    private double currentAgeYear;

    private Stats stats;

    private Events lifeEvents = new Events();

    private Long id;

    public CastMember (String race) {
        this.race = race;



        //load race details
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
        Iterable<Race> raceNodes = session.query(Race.class,"MATCH n=(r:RACE)-[*1..5]->() WHERE r.name='"+race+"' return n as RACE",Collections.EMPTY_MAP );

        for (Race raceNode: raceNodes) {
            Sex sex = raceNode.getGender().pickRandomSex();
            this.gender = sex.getType();
            this.firstName = sex.getNames().pickRandomFirstName().getName();
            this.familyName = sex.getNames().getSurnames().pickRandomSurname();
            this.stats = new Stats(raceNode.getStats());
            this.stats.rollStats();
            this.stats.tableStats();
            this.stats.workoutDerivedStats();
            // Set Age
            int years = (int) Dice.roll("6D6");
            if (years <17 ){
                years = 17;
            }

            int days = (int) Dice.roll("1D365");
            this.lifeEvents.loadCalendar();
            this.lifeEvents.addNpcLife(years,days);

        }
    }

    public String toFormatedString(){
       String ret = "WS\tBS\tS\tT\tAg\tInt\tWP\tFel\n";

       List<Integer> stat = new ArrayList<>();

       for(int f = 0; f < 16 ; f++){
           stat.add(0);
       }

        for (Of of: this.stats.getOf()) {
            Stat stata = of.getEnd();
            int i = stata.getOrder()-1;
            stat.set(i,stata.getValue());
        }

        for(int f = 0; f < 8; f++) {
            ret = ret + stat.get(f)+"\t";
        }

        ret = ret + "\n";

        ret = ret + "A\tW\tSB\tTB\tM\tMag\tIP\tFP\n";

        for(int f = 8; f < 16; f++) {
            ret = ret + stat.get(f)+"\t";
        }

        ret = ret + "\n";

        return ret;
    }


}
