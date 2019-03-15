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
            return Optional.of(modelMapper.map(section,SectionDTO.class));
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<SectionDTO> patch(SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = Optional.ofNullable(sectionDTO);
        if (optionalDTO.isPresent() && sectionRepository.findById(optionalDTO.get().getId()).isPresent()) {
            SectionDTO dto = modelMapper.map(this.get(optionalDTO.get().getId()), SectionDTO.class);
            modelMapper.map(optionalDTO.get(), dto);
            return update(dto);
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<SectionDTO> update(SectionDTO sectionDTO) {
        Optional<SectionDTO> optionalDTO = Optional.of(sectionDTO);
        String id = optionalDTO.get().getId();
        if (id != null && sectionRepository.findById(id).isPresent()) {
            Section section = modelMapper.map(optionalDTO.get(), Section.class);
            section.setCreatedDate(sectionRepository.findById(id).get().getCreatedDate());
            section.setModifiedDate(new Date());
            section = sectionRepository.save(section);
            return Optional.of(modelMapper.map(section,SectionDTO.class));
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
}
