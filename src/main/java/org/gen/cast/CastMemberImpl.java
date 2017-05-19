package org.gen.cast;

import org.gen.service.GenericService;

/**
 * Created by Daniel on 17/05/2017.
 */
public class CastMemberImpl extends GenericService<CastMember> {
    @Override
    public Class<CastMember> getEntityType() {
        return CastMember.class;
    }
}
