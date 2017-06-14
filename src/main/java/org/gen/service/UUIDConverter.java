package org.gen.service;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import java.util.UUID;

/**
 * Created by Daniel on 31/05/2017.
 */
public class UUIDConverter implements AttributeConverter<UUID,String> {
    @Override
    public String toGraphProperty(UUID value) {
        return value.toString();
    }

    @Override
    public UUID toEntityAttribute(String value) {
        return UUID.fromString(value);
    }


}
