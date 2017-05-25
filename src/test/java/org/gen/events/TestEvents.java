package org.gen.events;

/**
 * Created by Daniel on 15/05/2017.
 */
public class TestEvents {
    public static void main(String[] args) {
        Events events = new Events();

        events.addNpcLife(18,46);

        EventsImpl events1 = new EventsImpl();

        events1.createOrUpdate(events);
    }
}
