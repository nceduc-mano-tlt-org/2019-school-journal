package ru.nceduc.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nceduc.journal.dto.SectionDTO;
import ru.nceduc.journal.entity.Section;
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
    private final ModelMapper modelMapper;

    @Override
    public Optional<SectionDTO> get(String id) {
        return sectionRepository.findById(id).map(section -> modelMapper.map(section, SectionDTO.class));
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
            return Optional.of(modelMapper.map(section, SectionDTO.class));
        } else
            return Optional.empty();
    }

    @Override
    public Optional<SectionDTO> patch(SectionDTO sectionDTO) {
        Optional<SectionDTO> patchedOptional = Optional.ofNullable(sectionDTO);
        if (patchedOptional.isPresent()) {
            Optional<SectionDTO> currentOptional = get(sectionDTO.getId());

            if (currentOptional.isPresent()) {
                SectionDTO patchedDTO = patchedOptional.get();
                SectionDTO currentDTO = currentOptional.get();
                modelMapper.map(patchedDTO, currentDTO);
                return save(currentDTO);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<SectionDTO> update(SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = Optional.ofNullable(sectionDTO);
        if (optionalDTO.isPresent()) {
            return save(optionalDTO.get());
        }
        return Optional.empty();
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
        List<SectionDTO> sectionDTO = new ArrayList<>();
        sectionRepository.findAllByProjectId(projectId, Sort.by("createdDate").ascending()).forEach(section -> {
            sectionDTO.add(modelMapper.map(section, SectionDTO.class));
        });
        return sectionDTO;
    }

    private Optional<SectionDTO> save(SectionDTO sectionDTO) {
        Section section = modelMapper.map(sectionDTO, Section.class);

        return sectionRepository.findById(section.getId()).map(entity -> {
            section.setModifiedDate(new Date());
            section.setCreatedDate(entity.getCreatedDate());
            section.setProject(entity.getProject());
            Section savedSection = sectionRepository.save(section);
            return modelMapper.map(savedSection, SectionDTO.class);
        });
    }
}
