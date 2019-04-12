package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @ApiModelProperty(readOnly = true)
    private String id;
    private String username;
    private String password;
    @ApiModelProperty(readOnly = true)
    private String projectId;
}
