package org.gen.dates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 12/05/2017.
 */
public class TestDateTime {
    public static void main(String[] args) {

        boolean creatOrLoad = true;

        CalendarImpl calendarImpl = new CalendarImpl();

    //    Calendar calendar = calendarImpl.find(23747L);
        DateTime dateTime = new DateTime("2550.03.01 10:41:34.000");

        DateTimeImpl dateTimel = new DateTimeImpl();

        if(creatOrLoad){
            try {
                dateTimel.createOrUpdate( dateTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            DateTime loadedDateTime;
            Iterable<DateTime> dateTimes = dateTimel.findAll();
            dateTimes.forEach(dateTime2->{
               System.out.println(dateTime2.toString());
        //       System.out.println(""+calendar.dayOfTheWeek(dateTime2));
            });
        }






    }
}
