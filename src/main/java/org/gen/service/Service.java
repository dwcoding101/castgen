package org.gen.service;

import java.util.UUID;

/**
 * Created by Daniel on 02/05/2017.
 */
public interface Service<T> {
    Iterable<T> findAll();
    T find(Long id);
    T find(String uuid);
    void delete(Long id);
    T createOrUpdate(Id<T> object);
    void update(Id<T> object);
}
