package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student extends Person {

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Payment> payments;

    @OneToOne(mappedBy = "student", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Wallet wallet;

}