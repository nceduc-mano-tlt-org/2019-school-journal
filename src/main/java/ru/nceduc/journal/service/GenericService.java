package ru.nceduc.journal.service;

import java.util.List;

public interface GenericService <E> {

    void create(E entity);

    void delete(String id);

    void patch(String id,E entity);

    void update(String id,E entity);

    E get(String id);

    List<E> getAll();

}
