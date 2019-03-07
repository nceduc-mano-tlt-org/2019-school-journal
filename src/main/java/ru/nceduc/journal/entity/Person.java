package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class Person extends AbstractEntity{
    private String firstName;
    private String lastName;
}
