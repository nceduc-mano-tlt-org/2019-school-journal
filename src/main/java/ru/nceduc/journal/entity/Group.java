package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends AbstractEntity {

    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @OneToMany(mappedBy = "group",  fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Teacher> teachers;




    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
