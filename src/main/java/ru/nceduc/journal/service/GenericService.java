package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService<E> {

    E get(String id);

    List<E> getAll();

    boolean create(E entity, String parentId);

    boolean update(E entity);

    boolean delete(String id);

}
