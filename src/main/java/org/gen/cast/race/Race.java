package org.gen.cast.race;

import org.gen.cast.description.RacialDescription;
import org.gen.cast.gender.Genders;
import org.gen.cast.stat.Stats;
import org.gen.cast.stat.WithStats;
import org.gen.cast.gender.WithGenders;
import org.gen.service.Id;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label="RACE")
public class Race implements Id {



    @GraphId
    private Long id;

    private RacialDescription racialDescription;

    @Property(name="name")
    private String name;

    public Race() {
    }
// stats go here when i work out how list work;

    @Relationship(type = WithGenders.TYPE)
    private List<WithGenders> withGender = new ArrayList<>();

    @Relationship(type = WithStats.TYPE)
    private List<WithStats> withStats = new ArrayList<>();

    public RacialDescription getRacialDescription() {
        return racialDescription;
    }

    public void setRacialDescription(RacialDescription racialDescription) {
        this.racialDescription = racialDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWith(Genders target) {
        this.withGender.add(new WithGenders(this,target));
    }

    public void addWith(Stats target) {
        this.withStats.add(new WithStats(this,target));
    }

    public int nowManyGenders() {
        return this.withGender.size();
    }

    public Long getId() {
        return id;
    }



    public Genders getGender(){
        Genders ret = null;
        if(!this.withGender.isEmpty()) {
            ret = this.withGender.get(0).getEnd();
        }
        return ret;
    }

    public Stats getStats(){
        Stats ret = null;
        if(!this.withStats.isEmpty()) {
            ret = this.withStats.get(0).getEnd();
        }
        return ret;
    }
}
