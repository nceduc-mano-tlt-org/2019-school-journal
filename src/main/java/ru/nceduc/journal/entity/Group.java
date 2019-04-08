package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends AbstractEntity {

    private String name;
    private String description;
    private Date startDate;
    private long cost;

    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @OneToMany(mappedBy = "group",  fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToMany (mappedBy="group", fetch = FetchType.LAZY)
    private Set<Attendance> attendance = new HashSet<>();


    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
