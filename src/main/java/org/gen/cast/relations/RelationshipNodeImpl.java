package org.gen.cast.relations;

import org.gen.service.GenericService;

/**
 * Created by Daniel on 05/06/2017.
 */
public class RelationshipNodeImpl extends GenericService<RelationshipNode> {
    @Override
    public Class<RelationshipNode> getEntityType() {
        return RelationshipNode.class;
    }
}
