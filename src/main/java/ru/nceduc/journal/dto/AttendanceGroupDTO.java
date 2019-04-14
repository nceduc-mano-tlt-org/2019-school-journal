package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class AttendanceGroupDTO {
    @ApiModelProperty(readOnly = true)
    String id;
    String groupId;
    int month;
    int year;
//    List<AttendanceStudentDTO> attendanceStudentDTOS;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date dateVisit;

}
