package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.controller.dto.GroupDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.service.GroupService;
import ru.nceduc.journal.service.SectionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final SectionService sectionService;
    private final ModelMapper modelMapper;

    @Override
    public GroupDTO get(String id) {
        Group group;
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            group = groupOptional.get();
            return modelMapper.map(group, GroupDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<GroupDTO> getAll() {
        List<Group> groups = groupRepository.findAll(Sort.by("createdDate").ascending());
        List<GroupDTO> groupsDTO = new ArrayList<>();
        for(Group group : groups) {
            groupsDTO.add(modelMapper.map(group, GroupDTO.class));
        }
        return groupsDTO;
    }

    @Override
    public GroupDTO create(GroupDTO groupDTO) {
        if (groupDTO != null) {
            Group group = modelMapper.map(groupDTO, Group.class);
            groupRepository.save(group);
            return groupDTO;
        } else {
            return null;
        }
    }

    @Override
    public GroupDTO patch(GroupDTO entity) {
        //TODO
        return null;
    }

    @Override
    public GroupDTO update(GroupDTO groupDTO) {
        String id = groupDTO.getId();
        if (id != null && groupRepository.existsById(id)) {
            Group group = modelMapper.map(groupDTO, Group.class);
            group.setModifiedDate(new Date());
            groupRepository.save(group);
            return groupDTO;
        } else {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        if (id != null && groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        }
    }

    @Override
    public List<GroupDTO> getAllBySectionId(String sectionId) {
        Section section = sectionService.get(sectionId);
        List<Group> groups = groupRepository.findAllBySection(section, Sort.by("createdDate").ascending());
        List<GroupDTO> groupsDTO = new ArrayList<>();
        for(Group group : groups) {
            groupsDTO.add(modelMapper.map(group, GroupDTO.class));
        }
        return groupsDTO;

    }
}
