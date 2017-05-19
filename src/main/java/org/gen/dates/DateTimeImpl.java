package org.gen.dates;

import org.gen.service.GenericService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Daniel on 12/05/2017.
 */
public class DateTimeImpl extends GenericService<DateTime> {

    public void createOrUpdate(Calendar calendar, DateTime object) {
        object = calendar.validate(object);
        if (object != null) {
            session.save(object,DEPTH_ENTITY);
        }
    }

    public List<DateTime> findAll() {

        List<DateTime> dateTimes  = new ArrayList<>();
        Iterable<DateTime> loadedDates = session.loadAll(getEntityType(),this.DEPTH_LIST);

        for (DateTime dateTime: loadedDates) {
            dateTime.processDateTime();
            dateTimes.add(dateTime);
        }

        return dateTimes;
    }

    @Override
    public Class<DateTime> getEntityType() {
        return DateTime.class;
    }
}
