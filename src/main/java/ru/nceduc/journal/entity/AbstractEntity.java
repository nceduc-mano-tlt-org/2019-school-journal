package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Setter
    private String id;
    private Date createdDate;
    @Setter
    private Date modifiedDate;

    public AbstractEntity() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }
}
