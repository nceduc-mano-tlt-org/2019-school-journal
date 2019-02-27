package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService<E> {

    E get(String id);

    List<E> getAll();

    E create(E entity);

    E patch(E entity);

    E update(E entity);

    void delete(String id);
}