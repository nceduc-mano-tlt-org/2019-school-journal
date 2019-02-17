package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService <E> {

    boolean create(E entity, String parentId);

    boolean delete(String id);

    boolean update(String id, E entity);

    E get(String id);

    List<E> getAll();

}
