package org.gen.cast.stat;

import org.gen.cast.stat.Of;
import org.gen.random.Dice;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 04/05/2017.
 */
@NodeEntity(label = Stats.TYPE)
public class Stats {
    @Transient
    public static final String TYPE = "STATS";
    @GraphId
    private Long id;

    @Relationship(type = Of.TYPE)
    private Set<Of> of = new HashSet<>();

    public void addStat(Stat target) {
        this.of.add(new Of(this, target));
    }

    public Stats() {
    }

    public void rollStats() {
        for (Of of: this.of) {
            String dice = of.getEnd().getGeneratedBy();
            if(dice.contains("DerivedFrom")){

            } else {
                of.getEnd().generateValue();
            }
        }
    }

    public void tableStats(){
        int min = 0;
        int max = 0;
        int value = 0;
        for (Of of: this.of) {
            String dice = of.getEnd().getGeneratedBy();
            if(dice.contains("RandomTableSelect")) {

                final String regex = "RandomTableSelect\\('([^']+)',(.*)\\)";
                final String string = dice;


                final Pattern pattern = Pattern.compile(regex);
                final Matcher matcher = pattern.matcher(string);

                while (matcher.find()) {
                  //  System.out.println("Full match: " + matcher.group(0));

                    String diceString = matcher.group(1);
                    String table = matcher.group(2);

                    int rolledValue = (int) Dice.roll(diceString);

                    final String regexa = "'(\\d+-\\d+:\\d+|\\d+:\\d+)'";

                    final Pattern patterna = Pattern.compile(regexa);
                    final Matcher matchera = patterna.matcher(table);

                    while (matchera.find()) {
                    //    System.out.println("Full match: " + matchera.group(0));
                        for (int f = 1; f <= matchera.groupCount(); f++) {
                            String tableItem = matchera.group(f);
                            if (tableItem.contains("-")) {
                                String[] parts = tableItem.split("-");
                                min = Integer.parseInt(parts[0]);
                                String[] secondPart = parts[1].split(":");
                                max = Integer.parseInt(secondPart[0]);
                                value = Integer.parseInt(secondPart[1]);

                            } else {
                                String[] secondPart = tableItem.split(":");
                                min = Integer.parseInt(secondPart[0]);
                                max = Integer.parseInt(secondPart[0]);
                                value = Integer.parseInt(secondPart[1]);
                            }
                            if (rolledValue >= min && rolledValue <= max) {
                                of.getEnd().setValue(value);
                            }
                        }
                    }
                }
            }
        }
    }

    public void workoutDerivedStats(){
        double calculatedValue = 0;
        int statValue = 0;
        for (Of of: this.of) {
            String dice = of.getEnd().getGeneratedBy();
            if(dice.contains("DerivedFrom")) {
                final String regex = "DerivedFrom\\('(.*)([\\/|+|*|-])(.*)'\\)";
                final String string = "DerivedFrom('S/10')";

                final Pattern pattern = Pattern.compile(regex);
                final Matcher matcher = pattern.matcher(dice);

                while (matcher.find()) {
                    String stat = matcher.group(1);
                    String math = matcher.group(2);
                    String number = matcher.group(3);

                    //Get Stat value
                    for (Of ofStat: this.of){
                        if(ofStat.getEnd().getName().equals(stat)){
                            statValue = ofStat.getEnd().getValue();
                        }
                    }

                    switch (math) {
                        case "/":
                            calculatedValue = (double)statValue/Double.parseDouble(number);
                            break;
                    }
                }

                of.getEnd().setValue((int) Math.floor(calculatedValue));
            }
        }
    }

    public Set<Of> getOf() {
        return of;
    }

    public Stats(Stats copy ) {
        for (Of of : copy.of) {
            addStat(new Stat(of.getEnd()));
        }
    }

    public Stat getStat(String statName) {
        Stat ret = null;
        for (Of of : this.of) {
            if(of.getEnd().getName().equals(statName)){
                ret = of.getEnd();
            }
        }
        return ret;
    }
}
