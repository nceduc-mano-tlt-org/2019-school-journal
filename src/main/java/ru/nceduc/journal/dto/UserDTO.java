package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {
    @ApiModelProperty(readOnly = true)
    private String id;
    private String username;
    private String password;
    @ApiModelProperty(readOnly = true)
    private String projectId;
}
