package org.gen.dates;

import org.gen.cast.gender.Sex;
import org.gen.cast.race.Race;
import org.gen.cast.race.RaceImpl;

/**
 * Created by Daniel on 11/05/2017.
 */
public class TestCalendar {
    public static void main(String[] args) {
        boolean createOrLoad =true;
        CalendarImpl calendarImpl = new CalendarImpl();
        if(createOrLoad) {

            Calendar calendar = new Calendar();

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
        else {
            try {

                Iterable<Calendar> iterableRace = calendarImpl.findAll();

                iterableRace.forEach((calendar) -> {
                    System.out.println("Calendar");

                    System.out.println("Days in the week:= " + calendar.days.getNumberDaysInTheWeek());
                    System.out.println("Days in the year:= " + calendar.getDaysInYear());


                });
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
