package org.gen.factory;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Daniel on 05/05/2017.
 */
public class Neo4jSessionFactory {
    private static final SessionFactory sessionFactory = new SessionFactory("org.gen");
    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    //prevent external instantiation
    private Neo4jSessionFactory(){

    }

    public Session getNeo4jSessionFactory() {
        return sessionFactory.openSession();
    }
}
