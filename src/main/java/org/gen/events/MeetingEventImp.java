package org.gen.events;

import org.gen.service.GenericService;

/**
 * Created by Daniel on 05/06/2017.
 */
public class MeetingEventImp extends GenericService<MeetingEvent> {
    @Override
    public Class<MeetingEvent> getEntityType() {
        return MeetingEvent.class;
    }
}
