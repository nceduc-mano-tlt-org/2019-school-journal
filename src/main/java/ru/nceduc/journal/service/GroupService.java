package ru.nceduc.journal.service;

import ru.nceduc.journal.dto.GroupDTO;

import java.util.List;

public interface GroupService extends GenericService<GroupDTO> {

    List<GroupDTO> getAllBySectionId(String sectionId);

}