package org.gen.dates;

import org.gen.factory.Neo4jSessionFactory;
import org.gen.service.Id;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.session.Session;

import java.util.Collections;

/**
 * Created by Daniel on 10/05/2017.
 */
@NodeEntity(label = "CALENDAR")
public class Calendar implements Id {

    private static Calendar calendar = new Calendar().loadedCalendar();

    public static Calendar getIntance() {
        return calendar;
    }
    @GraphId
    private Long id;

    private static Calendar loadedCalendar(){
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();
        Iterable<Calendar> calendarNodes = session.query(Calendar.class,"MATCH n=(r:CALENDAR)-[*1..5]->() return n as CALENDAR", Collections.EMPTY_MAP );

        if(calendarNodes.iterator().hasNext()) {
            calendarNodes.forEach((calendarNode) -> {
                calendar = calendarNode;
            });
        }
        else {
            calendar = new Calendar();
        }

        return calendar;
    }

    Long secondsInMin = 60L;
    Long minsInHour = 60L;
    Long hoursInDay = 24L;

    private Long daysInYear;

    DateTime dateTimeRef = new DateTime("2520.01.01 00:00:01.0");
    Day dayRef = new Day(0L, "Wellentag");

    DateTime currentDate = new DateTime("2553.01.01 12:00:01.0");

    public DateTime getCurrentDate() {
        return currentDate;
    }

    private Calendar() {

    }


    public Long getHoursInDay() {
        return hoursInDay;
    }

    public Long getDaysInYear() {
        if (daysInYear == 0) {
            this.daysInYear = calculateDaysInAYear();
        }
        return daysInYear;
    }

    @Relationship(type="CAN_BE", direction=Relationship.OUTGOING)
    private Months months = new Months();

    @Relationship(type="CAN_BE", direction=Relationship.OUTGOING)
    public Days days = new Days();

    public long calculateDaysInAYear() {
        long dayCount = 0;

        for (Month month:months.months) {
            dayCount = dayCount + month.getDaysInTheMonth();
        }
        return dayCount;
    }

    public void addMonth(Month month) {
        months.months.add(month);
        this.daysInYear = calculateDaysInAYear();
    }

    public Long getNumberOfDaysMonths(Long month){
        return Long.parseLong("" +months.months.get(Integer.parseInt(""+(month-1))).getDaysInTheMonth());
    }

    public Long getNumberOfMonths(){
        return Long.parseLong("" +months.months.size());
    }

    public DateTime validate(DateTime dateTime){
        //check month
        int check = 0;

        if (!((dateTime.getMonth()>=1) &&(dateTime.getMonth())<= this.getNumberOfMonths())){
            check = check + 1;
        }

        //check day
        if(check==0) {
            if (!(dateTime.getDay() >= 1 && dateTime.getDay() <= this.getNumberOfDaysMonths(dateTime.getMonth()))) {
                check = check + 1;
            }
        }

        //check hours

        //check minutes

        //check secounds

        if(check == 0){
            return dateTime;
        }
        else{
            return null;
        }
    }

    public long daysBetween(DateTime dateTime) {



        return 0l;
    }

     public long daysSoFarThisYear(DateTime dateTime){
        long days = 0;

        for(int i =0; i<dateTime.getMonth()-1;i++){
            days = days + months.months.get(i).getDaysInTheMonth();
        }
        days = days + dateTime.getDay();


        return days;
    }

    public double secondsFromZero(DateTime dateTime){
        double totalSeconds = 0;

        totalSeconds = dateTime.getSeconds();
        totalSeconds = (totalSeconds) + (secondsInMin * dateTime.getMinutes());
        totalSeconds = totalSeconds + (secondsInMin * minsInHour)* dateTime.getHours();
        totalSeconds = totalSeconds + (secondsInMin * minsInHour * hoursInDay )*((daysSoFarThisYear(dateTime))-1);
        totalSeconds = totalSeconds + (secondsInMin* minsInHour * hoursInDay * getDaysInYear()) * dateTime.getYear();

        return totalSeconds;
    }

    public double secondsFromReference(DateTime dateTime){
        dateTimeRef.processDateTime();
        double refTimeTemp = secondsFromZero(dateTimeRef);
        double secondsDateTime = secondsFromZero(dateTime);
        double temp = secondsDateTime - refTimeTemp;
        return temp;
    }

    public double secondsToDays(double seconds){
       return seconds/(secondsInMin * minsInHour * hoursInDay );
    }

    public Long dayRefernce(double numberOfDays) {
        return (long) (numberOfDays-4) % this.days.getNumberDaysInTheWeek();
    }

    public String dayOfTheWeek(DateTime day) {
        double sec = this.secondsFromReference(day);
        double days = this.secondsToDays(sec);
        int dayref = (int)(days-4) % this.days.getNumberDaysInTheWeek();
        if(dayref<0) {
            dayref = dayref * -1;
        }
        return this.days.days.get(dayref).getNameOfDay();
    }

    public DateTime dateFromSeconds(Double seconds){
        double days = this.secondsToDays(seconds);
        long daysInAYear = this.getDaysInYear();
        long years = (long) Math.floor(days/daysInAYear);
        double daysleft = days - (years * daysInAYear);

        long months = 1;

        for (Month month: this.months.months) {
            if (month.getDaysInTheMonth() < daysleft) {
                daysleft =daysleft - month.getDaysInTheMonth();
                months++;
            }
            else {
                break;
            }
        }

        long day = (long)Math.ceil(daysleft);

        long numberOfHours = (long)(seconds % (secondsInMin * minsInHour * hoursInDay ) ) / (secondsInMin * minsInHour) ;
        long numberOfMinutes = (long) ((seconds % (secondsInMin * minsInHour * hoursInDay ) ) % (secondsInMin * minsInHour) ) / minsInHour;
        long numberOfSeconds = (long) ((seconds % (secondsInMin * minsInHour * hoursInDay ) ) % (secondsInMin * minsInHour) ) % secondsInMin  ;

        DateTime newDate = new DateTime(""+years+"."+months+"."+day+" "+numberOfHours+":"+numberOfMinutes+":"+numberOfSeconds+".000");

        return newDate;
    }

        public DateTime yearsDaysInThePast(long years, long days) {
            this.currentDate.processDateTime();
            double tempSeconds = secondsFromZero(this.currentDate);
            double tempYearsInSeconds = (years * getDaysInYear()) * (secondsInMin * minsInHour * hoursInDay );
            double tempDaysInSeconds = (days) * (secondsInMin * minsInHour * hoursInDay );

            double tempNewSeconds = tempSeconds - (tempYearsInSeconds + tempDaysInSeconds);

            DateTime dateTime = dateFromSeconds(tempNewSeconds);
            dateTime.processDateTime();
            return dateTime;
        }

    @Override
    public Long getId() {
        return this.id;
    }
}
