package ru.nceduc.journal.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<E> {

    Optional<E> get(String id);

    List<E> getAll();

    Optional<E> create(E entity);

    Optional<E> patch(E entity);

    Optional<E> update(E entity);

    void delete(String id);
}