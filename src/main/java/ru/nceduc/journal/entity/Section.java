package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sections")
public class Section extends AbstractEntity{
    private String name;
    private String description;
    @OneToMany(mappedBy = "section")
    private Set<Group> groups = new HashSet<>();

    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
