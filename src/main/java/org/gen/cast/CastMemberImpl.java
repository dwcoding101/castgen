package org.gen.cast;

import org.gen.service.GenericService;
import org.gen.service.Id;

/**
 * Created by Daniel on 17/05/2017.
 */
public class CastMemberImpl extends GenericService<CastMember> {
    @Override
    public Class<CastMember> getEntityType() {
        return CastMember.class;
    }

    @Override
    public void update(Id<CastMember> object) {
        session.save(object,-1);
    }
}
