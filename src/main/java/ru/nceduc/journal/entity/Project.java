package ru.nceduc.journal.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
@Data
public class Project extends AbstractEntity{

    private String name;
    @OneToOne(mappedBy = "project")
    private UserEntity user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Section> sections;

    public Project() {
        super();
        this.name = "default";
    }
}
