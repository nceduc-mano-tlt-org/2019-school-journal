package ru.nceduc.journal.service;

import java.util.List;
import java.util.Map;

public interface GenericService <E, DTO> {

    void create(E entity);

    void delete(String id);

    void patch(String id, Map<String, E> patchValues);

    void update(String id, E entity);

    DTO get(String id);

    List<DTO> getAll();

}
