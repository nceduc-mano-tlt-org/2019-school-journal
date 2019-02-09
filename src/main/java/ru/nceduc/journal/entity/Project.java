package ru.nceduc.journal.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "projects")
@Data
public class Project extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "idProject")
    private String id;
    private String nameProject;

    public Project(String id) {
        super(id);
        this.nameProject = "default";
    }
}
