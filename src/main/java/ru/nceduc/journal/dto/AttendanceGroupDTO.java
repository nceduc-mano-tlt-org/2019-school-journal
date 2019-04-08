package ru.nceduc.journal.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AttendanceGroupDTO {
    private String groupId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    
    private Date dateVisit;
}
