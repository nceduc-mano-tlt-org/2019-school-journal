package ru.nceduc.journal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attendances_filter")
public class AttendanceFilter extends AbstractEntity  {
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
    private int month;
    private int year;
}
