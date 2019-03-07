package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "students")
@Getter
@Setter
public class Student extends Person {


    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

/*
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Group> groupList;
*/
}