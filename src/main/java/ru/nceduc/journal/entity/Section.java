package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sections")
public class Section extends AbstractEntity{
    private String name;
    private String description;

    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
