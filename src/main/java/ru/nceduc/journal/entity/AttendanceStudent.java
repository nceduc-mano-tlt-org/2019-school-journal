package ru.nceduc.journal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "attendances-student")
public class AttendanceStudent {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne
    private Group group;

//    @ManyToOne
//    private AttendanceGroup attendanceGroup;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisit;

//    public AttendanceStudent() {
//        this.dateVisit = new LocalDate();
//    }
}
