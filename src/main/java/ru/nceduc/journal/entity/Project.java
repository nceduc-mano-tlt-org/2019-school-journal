package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project extends AbstractEntity {

    private String name;
    private String description;
    @OneToOne(mappedBy = "project")
    private UserEntity user;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Section> sections;

    public Project() {
        super();
        this.name = "default";
    }
}
