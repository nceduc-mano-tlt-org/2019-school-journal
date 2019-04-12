package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String groupId;
    @ApiModelProperty(readOnly = true)
    private Date lastDate;
}
