package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendances-group")
public class AttendanceGroup {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    private int month;
    private int year;

//    @OneToMany(fetch = FetchType.LAZY)
//    private AttendanceStudent attendanceStudent;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Student student;

//    @JsonFormat(pattern="yyyy-MM-dd")
//    private Date dateVisit;

//    public AttendanceGroup() {
//        this.dateVisit = new Date();
//    }
}
