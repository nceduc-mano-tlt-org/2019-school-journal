package ru.nceduc.journal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendances_student")
public class AttendanceStudent extends AbstractEntity  {
    @ManyToOne
    private Student student;
    @ManyToOne
    private Group group;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisit;
}
