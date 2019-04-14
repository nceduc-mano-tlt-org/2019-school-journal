package ru.nceduc.journal.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class AttendanceGroupDTO {
    private String groupId;
    private String month;
    private String year;
//    List<AttendanceStudentDTO> attendanceStudentDTOS;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateVisit;

}
