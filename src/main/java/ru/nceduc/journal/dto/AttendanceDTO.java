package ru.nceduc.journal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceDTO {
    private String id;
    private String studentId;
    private String groupId;
    private Date date;
}
