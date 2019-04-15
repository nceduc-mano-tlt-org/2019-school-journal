package ru.nceduc.journal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String groupId;
    @ApiModelProperty(readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastDate;
}
