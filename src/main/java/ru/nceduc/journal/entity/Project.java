package ru.nceduc.journal.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
