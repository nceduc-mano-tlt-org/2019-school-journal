package ru.nceduc.journal.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@Data
public class Project extends AbstractEntity{

    private String nameProject;
    @OneToOne(mappedBy = "project")
    private UserEntity user;

    public Project() {
        super();
        this.nameProject = "default";
    }
}
