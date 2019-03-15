package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.dto.SectionDTO;
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
    public Optional<GroupDTO> get(String id) {
        return groupRepository.findById(id).map(group -> modelMapper.map(group, GroupDTO.class));
    }

    @Override
    public List<GroupDTO> getAll() {
        List<GroupDTO> groupsDTO = new ArrayList<>();
        groupRepository.findAll(Sort.by("createdDate").ascending()).forEach(group -> {
            groupsDTO.add(modelMapper.map(group, GroupDTO.class));
        });
        return groupsDTO;
    }

    @Override
    public Optional<GroupDTO> create(GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = Optional.ofNullable(groupDTO);
        if (optionalDTO.isPresent()) {
            Group group = groupRepository.save(modelMapper.map(optionalDTO.get(), Group.class));
            return Optional.of(modelMapper.map(group,GroupDTO.class));
        }
            return Optional.empty();
    }

    @Override
    public Optional<GroupDTO> patch(GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = Optional.ofNullable(groupDTO);
        if (optionalDTO.isPresent() && groupRepository.findById(optionalDTO.get().getId()).isPresent()) {
            GroupDTO projectDTO = modelMapper.map(this.get(optionalDTO.get().getId()), GroupDTO.class);
            modelMapper.map(optionalDTO.get(), projectDTO);
            return update(projectDTO);
        }
        return Optional.empty();
    }

    @Override
    public Optional<GroupDTO> update(GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = Optional.of(groupDTO);
        String id = optionalDTO.get().getId();
        if (id != null && groupRepository.findById(id).isPresent()) {
            Group group = modelMapper.map(optionalDTO.get(), Group.class);
            group.setCreatedDate(groupRepository.findById(id).get().getCreatedDate());
            group.setModifiedDate(new Date());
            group = groupRepository.save(group);
            return Optional.of(modelMapper.map(group,GroupDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        if (id != null && groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        }
    }

    @Override
    public List<GroupDTO> getAllBySectionId(String sectionId) {
        List<GroupDTO> groupsDTO = new ArrayList<>();
        groupRepository.findAllBySectionId(sectionId, Sort.by("createdDate").ascending()).forEach(group -> {
            groupsDTO.add(modelMapper.map(group, GroupDTO.class));
        });
        return groupsDTO;
    }
}