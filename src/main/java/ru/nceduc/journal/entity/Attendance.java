package ru.nceduc.journal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "attendances")
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateVisit;

    public Attendance() {
        this.dateVisit = new Date();
    }
}
