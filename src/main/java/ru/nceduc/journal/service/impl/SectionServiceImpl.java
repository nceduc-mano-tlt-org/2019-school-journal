package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.SectionRepository;
import ru.nceduc.journal.service.SectionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public Section get(String id) {
        Section section = null;
        Optional<Section> sectionOptional = sectionRepository.findById(id);
        if (sectionOptional.isPresent()) {
            section = sectionOptional.get();
        }
        return section;
    }

    @Override
    public List<Section> getAll() {
        return sectionRepository.findAll(Sort.by("createdDate").ascending());
    }

    @Override
    public Section create(Section entity) {
        if (entity != null) {
            sectionRepository.save(entity);
        }
        return entity;
    }

    @Override
    public Section patch(Section entity) {
        return null;
    }

    @Override
    public Section update(Section entity) {
        String id = entity.getId();
        if (id != null && sectionRepository.existsById(id)) {
            Section section = this.get(id);
            section.setName(entity.getName());
            section.setDescription(entity.getDescription());
            section.setModifiedDate(new Date());
            sectionRepository.save(section);
            return section;
        } else {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        Section section = this.get(id);
        if (id != null && sectionRepository.existsById(id) && section.getGroups().size() == 0) {
            sectionRepository.deleteById(id);
        }
    }

}
