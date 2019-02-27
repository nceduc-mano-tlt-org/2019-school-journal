package ru.nceduc.journal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date modifiedDate;

    public AbstractEntity() {
        this.createdDate = new Date();
        this.modifiedDate = new Date();
    }
}
