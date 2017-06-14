package org.gen.dates.wfrp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.chrono.Era;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;




/**
 * Created by Daniel on 08/06/2017.
 */
public class WFRPChronoLocalDate extends ChronoLocalDateImpl<WFRPChronoLocalDate>
       implements ChronoLocalDate, Serializable {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -315327627230580583L;

    /**
     * The underlying ISO local date.
     */
//    private final transient LocalDate isoDate;

    /**
     * The WFRP Era of this date.
     */
    private transient Era era;

    private transient int yearOfEra;

    @Override
    WFRPChronoLocalDate plusYears(long yearsToAdd) {
        return null;
    }

    @Override
    WFRPChronoLocalDate plusMonths(long monthsToAdd) {
        return null;
    }

    @Override
    WFRPChronoLocalDate plusDays(long daysToAdd) {
        return null;
    }

    @Override
    public Chronology getChronology() {
        return null;
    }

    @Override
    public int lengthOfMonth() {
        return 0;
    }

    @Override
    public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
        return null;
    }

    @Override
    public long getLong(TemporalField field) {
        return 0;
    }
}
