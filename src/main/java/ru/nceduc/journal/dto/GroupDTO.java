package ru.nceduc.journal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {
    private String id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private long cost;
    private String sectionId;
}
