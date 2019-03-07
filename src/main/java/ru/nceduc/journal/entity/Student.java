package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table (name = "students")
@Getter
@Setter
public class Student extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}