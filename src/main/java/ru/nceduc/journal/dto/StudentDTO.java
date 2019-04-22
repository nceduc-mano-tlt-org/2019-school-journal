package ru.nceduc.journal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM")
    private Date lastDate;
    private long walletBalance;
}
