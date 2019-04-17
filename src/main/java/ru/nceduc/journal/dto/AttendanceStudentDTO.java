package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AttendanceStudentDTO {
    @ApiModelProperty(readOnly = true)
    String id;
    String studentId;
    String groupId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateVisit;
}
