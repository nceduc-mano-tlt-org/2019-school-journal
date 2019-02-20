package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.GroupRepository;
import ru.nceduc.journal.service.GroupService;
import ru.nceduc.journal.service.SectionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final SectionService sectionService;

    @Override
    public Group get(String id) {
        Group group = null;
        Optional<Group> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            group = groupOptional.get();
        }
        return group;
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll(Sort.by("createdDate").ascending());
    }

    @Override
    public Group create(Group entity) {
        if (entity != null) {
            groupRepository.save(entity);
        }
        return entity;
    }

    @Override
    public Group patch(Group entity) {
        return null;
    }

    @Override
    public Group update(Group entity) {
        String id = entity.getId();
        if (id != null && groupRepository.existsById(id)) {
            Group group = this.get(id);
            group.setName(entity.getName());
            group.setDescription(entity.getDescription());
            group.setModifiedDate(new Date());
            groupRepository.save(group);
            return group;
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
    public List<Group> getAllBySectionId(String sectionId) {
        Section section = sectionService.get(sectionId);
        return groupRepository.findAllBySection(section, Sort.by("createdDate").ascending());
    }
}
