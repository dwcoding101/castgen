package org.gen.cast.gender;

import org.gen.random.Dice;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label = Genders.TYPE)
public class Genders {
    @Transient
    public static final String TYPE = "GENDERS";

    public Genders() {
    }

    @GraphId
    private Long id;

    @Relationship(type = Of.TYPE)
    private List<Of> of = new ArrayList<>();

    public void addSex(Sex target) {
        this.of.add(new Of(this, target));
    }

    public int howManySexes(){
        if (!of.isEmpty()){
            return of.size();
        } else {
            return 0;
        }
    }

    public Sex pickRandomSex() {
        int size = of.size();

        int randomPick = (int) Dice.roll("1D"+size);

        return of.get(randomPick-1).getEnd();


    }

    public Sex getSex(String sex) {
        return this.of.stream().filter(t -> t.getEnd().getType().equals(sex)).findFirst().get().getEnd();
    }




}
