package ru.nceduc.journal.dto;

import lombok.Data;
import ru.nceduc.journal.entity.Group;

import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
public class SectionDTO {
    private String id;
    private String name;
    private String description;
}
