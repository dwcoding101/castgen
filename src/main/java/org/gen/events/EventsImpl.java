package org.gen.events;

import org.gen.cast.relations.Relations;
import org.gen.dates.Calendar;
import org.gen.service.GenericService;

import java.util.UUID;

/**
 * Created by Daniel on 16/05/2017.
 */
public class EventsImpl extends GenericService<Events>{



    @Override
    public Class<Events> getEntityType() {
        return Events.class;
    }
}
