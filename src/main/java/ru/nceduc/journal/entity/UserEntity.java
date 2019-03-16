package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "usr")
@Getter
@Setter
public class UserEntity extends AbstractEntity {

    private String username;
    private String password;
    private boolean active;


    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public UserEntity(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
