package org.gen.cast.name;

import org.gen.random.Dice;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label= Surnames.TYPE)
public class Surnames {

    public Surnames() {
    }

    public static final String TYPE = "SURNAMES";
    @GraphId
    private Long id;

    @Relationship(type=OfSurnamePart1.TYPE)
    private Set<OfSurnamePart1> ofSurnamePart1s = new HashSet<>();

    @Relationship(type=OfSurnamePart2.TYPE)
    private Set<OfSurnamePart2> ofSurnamePart2s = new HashSet<>();

    public void addSurnamePart1(SurnamePart1 target) {
        this.ofSurnamePart1s.add(new OfSurnamePart1(this, target));
    }

    public void addSurnamePart2(SurnamePart2 target) {
        this.ofSurnamePart2s.add(new OfSurnamePart2(this, target));
    }

    public void loadSurnamesPart1(String firstNames) {

        InputStream is =  getClass().getClassLoader().getResourceAsStream(firstNames);
        Scanner in = new Scanner(is);
        long i = 1;
        while( in.hasNextLine()) {
            String surnamePart1 = in.nextLine();
            System.out.println(surnamePart1);
            SurnamePart1 surnamePart1Node =new SurnamePart1(surnamePart1, i);
            addSurnamePart1(surnamePart1Node);
            i++;
        }

        System.out.println("size:= " + ofSurnamePart1s.size());
    }

    public void loadSurnamesPart2(String firstNames) {

        InputStream is =  getClass().getClassLoader().getResourceAsStream(firstNames);
        Scanner in = new Scanner(is);
        long i = 1;
        while( in.hasNextLine()) {
            String surnamePart2 = in.nextLine();
            System.out.println(surnamePart2);
            SurnamePart2 surnamePart2Node =new SurnamePart2(surnamePart2, i);
            addSurnamePart2(surnamePart2Node);
            i++;
        }

        System.out.println("size:= " + ofSurnamePart1s.size());
    }

    public String pickRandomSurname() {
        int numberOfSurnamesPart1 = ofSurnamePart1s.size();
        int numberOfSurnamesPart2 = ofSurnamePart2s.size();

        int randomPart1 = (int) Dice.roll("1D"+numberOfSurnamesPart1);
        int randomPart2 = (int) Dice.roll("1D"+numberOfSurnamesPart2);

        SurnamePart1 surnamePart1 = ofSurnamePart1s.stream().filter(t->t.getEnd().getNumber() == randomPart1).findFirst().get().getEnd();
        SurnamePart2 surnamePart2 = ofSurnamePart2s.stream().filter(t->t.getEnd().getNumber() == randomPart2).findFirst().get().getEnd();


        return surnamePart1.getName()+surnamePart2.getName();

    }

}
