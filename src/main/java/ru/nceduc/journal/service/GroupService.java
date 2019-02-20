package ru.nceduc.journal.service;

import ru.nceduc.journal.controller.dto.GroupDTO;
import ru.nceduc.journal.entity.Group;

import java.util.List;

public interface GroupService extends GenericService<GroupDTO> {

    List<GroupDTO> getAllBySectionId(String sectionId);

}
