package org.gen.dates;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by Daniel on 10/05/2017.
 */
@NodeEntity(label = Year.LABEL)
public class Year {
    public static final String LABEL = "YEAR_INFO";
}
