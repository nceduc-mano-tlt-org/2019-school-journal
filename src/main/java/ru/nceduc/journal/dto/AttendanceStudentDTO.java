package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AttendanceStudentDTO {
    @ApiModelProperty(readOnly = true)
    private String id;
    private String studentId;
    private String groupId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisit;
}
