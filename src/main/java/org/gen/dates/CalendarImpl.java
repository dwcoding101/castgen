package org.gen.dates;

import org.gen.cast.race.Race;
import org.gen.service.GenericService;

import java.util.*;

/**
 * Created by Daniel on 11/05/2017.
 */
public class CalendarImpl extends GenericService<Calendar> {


    @Override
    public Class<Calendar> getEntityType() {
        return Calendar.class;
    }
}
