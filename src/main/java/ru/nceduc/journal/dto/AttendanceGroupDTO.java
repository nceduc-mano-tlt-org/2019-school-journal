package ru.nceduc.journal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceGroupDTO {
    private String groupId;
    private Date dateVisit;
}
