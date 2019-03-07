package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "teachers")
@Getter
@Setter
public class Teacher extends Person {

    @ManyToOne (fetch = FetchType.LAZY)
    private Group groups;
/*
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    private List<Group> groupList;
*/
}
