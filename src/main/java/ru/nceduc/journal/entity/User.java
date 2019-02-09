package ru.nceduc.journal.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends AbstractEntity{

    private String login;
    private String password;

    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }
}
