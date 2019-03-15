package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sections")
public class Section extends AbstractEntity {
    private String name;
    private String description;

    @OneToMany(mappedBy = "section", fetch = FetchType.LAZY)
    private Set<Group> groups = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
