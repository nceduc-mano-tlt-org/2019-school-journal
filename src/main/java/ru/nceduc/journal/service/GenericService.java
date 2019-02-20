package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService <E> {

    E create(E entity);

    void delete(String id);

    E patch(E entity);

    E update(E entity);

    E get(String id);

    List<E> getAll();

}
