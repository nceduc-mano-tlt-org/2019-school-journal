package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService <E> {

    E create(E entity, String parentId);

    void delete(String id);

    E update(E entity);

    E patch(E entity);

    E get(String id);

    List<E> getAll();

}
