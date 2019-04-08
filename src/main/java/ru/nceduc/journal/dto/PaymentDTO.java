package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PaymentDTO {
    @ApiModelProperty(readOnly = true)
    String id;
    String studentId;
    String groupId;
    long amount;
}
