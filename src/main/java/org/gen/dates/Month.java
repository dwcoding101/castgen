package org.gen.dates;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 10/05/2017.
 */

@NodeEntity(label = Month.LABEL)
public class Month {
    public static final String LABEL ="MONTH_OF_THE_YEAR";

    @GraphId
    private Long id;

    private Long number;
    private String nameOfMonth;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public Long getDaysInTheMonth() {
        return daysInTheMonth;
    }

    public void setDaysInTheMonth(Long daysInTheMonth) {
        this.daysInTheMonth = daysInTheMonth;
    }

    private Long daysInTheMonth;

    public Month() {
    }

    public Month(Long number, String nameOfMonth, Long daysInTheMonth) {

        this.number = number;
        this.nameOfMonth = nameOfMonth;
        this.daysInTheMonth = daysInTheMonth;
    }
}
