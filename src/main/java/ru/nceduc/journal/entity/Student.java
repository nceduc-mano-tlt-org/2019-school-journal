package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "students")
@Getter
@Setter
public class Student extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @OneToMany (mappedBy="student", fetch = FetchType.LAZY)
    private Set<Attendance> attendance = new HashSet<>();
}