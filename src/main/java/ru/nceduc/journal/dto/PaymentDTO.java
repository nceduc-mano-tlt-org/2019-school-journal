package ru.nceduc.journal.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    @ApiModelProperty(readOnly = true)
    String id;
    String studentId;
    String groupId;
    long amount;
}
