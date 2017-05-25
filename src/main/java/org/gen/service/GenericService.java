package org.gen.service;

import com.google.common.collect.Lists;
import org.gen.factory.Neo4jSessionFactory;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.AbstractList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 05/05/2017.
 */
public abstract class GenericService<T> implements Service<T> {
    protected static final int DEPTH_LIST = -1;
    protected static final int DEPTH_ENTITY = 5;
    protected Session session = Neo4jSessionFactory.getInstance().getNeo4jSessionFactory();


    @Override
    public void update(Id<T> object) {
        session.save(object,DEPTH_ENTITY);
    }

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(),DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id,DEPTH_ENTITY);
    }

    @Override
    public T find(String uuid) {

        Iterable<T> tIterable = session.query(getEntityType(),"Match p=(a{uuid:'"+uuid+"'})-[*]->() RETURN p",Collections.EMPTY_MAP);


        List aIterable = Lists.newArrayList(tIterable);

        return (T) aIterable.get(0);

    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(),id));
    }

    @Override
    public T createOrUpdate(Id<T> object) {
        session.save(object,DEPTH_ENTITY);
        return find(object.getId());
    }

    public abstract Class<T> getEntityType();
}
