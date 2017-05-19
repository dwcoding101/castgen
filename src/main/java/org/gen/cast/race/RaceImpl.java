package org.gen.cast.race;

import org.gen.service.GenericService;

/**
 * Created by Daniel on 05/05/2017.
 */
public class RaceImpl extends GenericService<Race>  {

    @Override
    public Iterable<Race> findAll() {
        return session.loadAll(getEntityType(), -1);
    }

    @Override
    public Class<Race> getEntityType() {
        return Race.class;
    }
}
