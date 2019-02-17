package ru.nceduc.journal.service;

import ru.nceduc.journal.entity.Group;

import java.util.List;

public interface GroupService extends GenericService<Group> {

    List<Group> getAllBySectionId(String sectionId);

}
