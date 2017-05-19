package org.gen.service;

import org.gen.factory.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Daniel on 05/05/2017.
 */
public abstract class GenericService<T> implements Service<T> {
    protected static final int DEPTH_LIST = -1;
    protected static final int DEPTH_ENTITY = -1;
    protected Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(),DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id,DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(),id));
    }

    @Override
    public void createOrUpdate(T object) {
        session.save(object,DEPTH_ENTITY);

    }

    public abstract Class<T> getEntityType();
}
