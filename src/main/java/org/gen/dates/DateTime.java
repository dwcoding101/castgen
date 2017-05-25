package org.gen.dates;

import org.gen.service.Id;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Transient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 11/05/2017.
 */
@NodeEntity(label="TIMESTAMP")
public class DateTime implements Id{
    public static final long VALID = 0b1;
    public static final long CHECKED = 0b10;

    @Transient
    private Long status = (long)0b00;
    @Transient
    private Long year;
    @Transient
    private Long month;
    @Transient
    private Long day;
    @Transient
    private Long hours;
    @Transient
    private Long minutes;
    @Transient
    private Long seconds;
    @Transient
    private Long hundreds;

    @Property(name="dateTime")
    private String dateTime;

    @GraphId
    private Long id;

    public DateTime(String dateTimeString) {

        dateTime = dateTimeString;
        processDateTime();
        dateTime = this.toString();
    }

    public void processDateTime(){

        final String regex = "(\\d+).(\\d+).(\\d+)\\s(\\d+):(\\d+):(\\d+)\\.(\\d+)";
        final String string = "2553.11.32 23:23:59.0";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(this.dateTime);

        while (matcher.find()) {
 //           System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
                switch (i) {
                    case 1:
                        year = Long.parseLong(matcher.group(i));
                        break;
                    case 2:
                        month = Long.parseLong(matcher.group(i));
                        break;
                    case 3:
                        day = Long.parseLong(matcher.group(i));
                        break;
                    case 4:
                        hours = Long.parseLong(matcher.group(i));
                        break;
                    case 5:
                        minutes = Long.parseLong(matcher.group(i));
                        break;
                    case 6:
                        seconds = Long.parseLong(matcher.group(i));
                        break;
                    case 7:
                        hundreds = Long.parseLong(matcher.group(i));
                        break;
                }
            }
        }
        this.dateTime = this.toString();
    }

    public DateTime() {

    }

    public String toString(){
        return ""+ year+"."+month+"."+day+" "+hours+":"+minutes+":"+seconds+"."+hundreds;
    }

    public Long getYear() {
        return year;
    }

    public Long getMonth() {
        return month;
    }

    public Long getDay() {
        return day;
    }

    public Long getHours() {
        return hours;
    }

    public Long getMinutes() {
        return minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public Long getHundreds() {
        return hundreds;
    }

    @Override
    public Long getId() {
        return id;
    }
}
