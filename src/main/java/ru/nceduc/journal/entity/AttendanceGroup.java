package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendances_group")
public class AttendanceGroup extends AbstractEntity  {
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
    private String month;
    private String year;

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
