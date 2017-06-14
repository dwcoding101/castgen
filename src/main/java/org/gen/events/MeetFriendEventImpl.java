package org.gen.events;

import org.gen.cast.relations.Relations;
import org.gen.service.GenericService;
import org.gen.service.Id;

/**
 * Created by Daniel on 05/06/2017.
 */
public class MeetFriendEventImpl extends GenericService<MeetFriendEvent> {

    @Override
    public void update(Id<MeetFriendEvent> object) {

        MeetFriendEvent event = (MeetFriendEvent) object;

        session.save(event, 1);

    }

    @Override
    public Class<MeetFriendEvent> getEntityType() {
        return MeetFriendEvent.class;
    }
}
