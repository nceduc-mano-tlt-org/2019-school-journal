package ru.nceduc.journal.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "idUser")
    private String id;
    private String login;
    private String password;

    public User(String id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }
}
