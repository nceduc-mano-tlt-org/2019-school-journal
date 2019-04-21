package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceFilterDTO {
    @ApiModelProperty(readOnly = true)
    String id;
    String groupId;
    int month;
    int year;
}
