package org.gen.cast.stat;

import org.gen.cast.race.Race;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Daniel on 04/05/2017.
 */
@RelationshipEntity(type = WithStats.TYPE)
public class WithStats {
    @Transient
    public static final String TYPE = "WITH_STATS";

    public WithStats() {
    }

    public Stats getEnd() {
        return end;
    }

    @GraphId

    private Long id;

    @StartNode
    private Race start;

    @EndNode
    private Stats end;

    public WithStats(Race start, Stats end) {
        this.start = start;
        this.end = end;
    }
}
