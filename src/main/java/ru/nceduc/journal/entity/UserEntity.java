package ru.nceduc.journal.entity;

import lombok.Data;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "usr")
@Data
public class UserEntity extends AbstractEntity {

    private String username;
    private String password;
    private boolean active;

    @OneToOne
    @JoinColumn(name="project_id")
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

    public UserEntity() {
    }
}
