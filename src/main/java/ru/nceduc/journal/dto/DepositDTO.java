package ru.nceduc.journal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DepositDTO {
    @JsonIgnore
    String id;
    String studentId;
    long amount;

    public DepositDTO() {
    }

    public DepositDTO(String studentId, long amount) {
        this.studentId = studentId;
        this.amount = amount;
    }
}
