package org.gen.cast.name;

import org.gen.cast.name.Of;
import org.gen.cast.gender.Sex;
import org.gen.random.Dice;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label=Names.TYPE)
public class Names {

    public Names() {
    }

    public static final String TYPE = "NAMES";
    @GraphId
    private Long id;

    @Relationship(type=Of.TYPE)
    private Set<Of> ofFirstName = new HashSet<>();

    @Relationship(type=OfSurnames.TYPE)
    private Set<OfSurnames> ofSurnames = new HashSet<>();


    public void addFirstName(FirstName target) {
        this.ofFirstName.add(new Of(this, target));
    }


    public void loadFirstNames(String firstNames) {

        InputStream is =  getClass().getClassLoader().getResourceAsStream(firstNames);
        Scanner in = new Scanner(is);
        long i = 1;
        while( in.hasNextLine()) {
            String firstNameString = in.nextLine();
            System.out.println(firstNameString);
            FirstName firstName =new FirstName(firstNameString, i);
            addFirstName(firstName);
            i++;
        }

        System.out.println("size:= " + ofFirstName.size());
    }


    public FirstName pickRandomFirstName() {
        int numberOfNames = ofFirstName.size();
        int random = (int) Dice.roll("1D"+numberOfNames);

        return ofFirstName.stream().filter(t->t.getEnd().getNumber() == random).findFirst().get().getEnd();
    }

    public void addSurnames(Surnames surnames) {
        this.ofSurnames.add(new OfSurnames(this, surnames));
    }

    public Surnames getSurnames() {
        return ofSurnames.stream().findFirst().get().getEnd();
    }
}
