package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.entity.Project;
import ru.nceduc.journal.entity.Section;
import ru.nceduc.journal.repository.ProjectRepository;
import ru.nceduc.journal.repository.SectionRepository;
import ru.nceduc.journal.service.SectionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<SectionDTO> get(String id) {
        if(sectionRepository.findById(id).isPresent()){
            return Optional.of(modelMapper.map(sectionRepository.findById(id), SectionDTO.class));
        }
        else
            return Optional.empty();
    }

    @Override
    public List<SectionDTO> getAll() {
        List<SectionDTO> sectionDTO = new ArrayList<>();
        sectionRepository.findAll(Sort.by("createdDate").ascending()).forEach(section -> {
            sectionDTO.add(modelMapper.map(section, SectionDTO.class));
        });
        return sectionDTO;
    }

    @Override
    public Optional<SectionDTO> create(SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = Optional.ofNullable(sectionDTO);
        if (optionalDTO.isPresent()) {
            Section section = sectionRepository.save(modelMapper.map(optionalDTO.get(), Section.class));
            return Optional.of(modelMapper.map(section,SectionDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public SectionDTO patch(SectionDTO sectionDTO) {
        String id = sectionDTO.getId();
        SectionDTO mainDTO = this.get(id);
        modelMapper.map(sectionDTO, mainDTO);
        return update(mainDTO);
    }

    @Override
    public SectionDTO update(SectionDTO sectionDTO) {
        String id = sectionDTO.getId();
        if (id != null && sectionRepository.existsById(id)) {
            Section section = modelMapper.map(sectionDTO, Section.class);
            section.setCreatedDate(sectionRepository.findById(id).get().getCreatedDate());
            section.setModifiedDate(new Date());
            sectionRepository.save(section);
            return sectionDTO;
        } else {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        Section section = modelMapper.map(this.get(id), Section.class);
        if (id != null && sectionRepository.existsById(id) && section.getGroups().size() == 0) {
            sectionRepository.deleteById(id);
        }
    }

    @Override
    public List<SectionDTO> getAllByProjectId(String projectId) {
        Project project = projectRepository.findById(projectId).get();
        List<SectionDTO> sectionDTO = new ArrayList<>();
        sectionRepository.findAllByProject(project, Sort.by("createdDate").ascending()).forEach(section -> {
            sectionDTO.add(modelMapper.map(section, SectionDTO.class));
        });
        return sectionDTO;
    }
}
