package org.gen.service;

/**
 * Created by Daniel on 02/05/2017.
 */
public interface Service<T> {
    Iterable<T> findAll();
    T find(Long id);
    void delete(Long id);
    void createOrUpdate(T object);
}
