package ru.nceduc.journal.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
@Data
public class Project extends AbstractEntity{

    private String nameProject;

    public Project() {
        super();
        this.nameProject = "default";
    }
}
