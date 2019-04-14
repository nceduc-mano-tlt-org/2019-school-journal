package ru.nceduc.journal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GroupDTO {
    private String id;
    private String name;
    private String description;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date startDate;
    private long cost;
    private String sectionId;
}
