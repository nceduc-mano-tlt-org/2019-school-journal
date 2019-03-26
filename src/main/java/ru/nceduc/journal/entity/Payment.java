package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends AbstractEntity {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Group group;
    private long amount;
}
