package ru.nceduc.journal.entity;

import lombok.Data;
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
@Table(name = "groups")
public class Group extends AbstractEntity {

    private String name;
    private String description;
    @ManyToOne
    private Section section;

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
