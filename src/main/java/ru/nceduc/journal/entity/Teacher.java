package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table (name = "teachers")
@Getter
@Setter
public class Teacher extends Person {

    @ManyToOne (fetch = FetchType.LAZY)
    private Group group;

}
