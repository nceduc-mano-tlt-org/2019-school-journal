package ru.nceduc.journal.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDTO {
    private String id;
    private Date createdDate;
    private Date modifiedDate;
    private String firstName;
    private String lastName;

    @Override
    public String toString(){
        return String.format(
                "Teacher[id=%s, createdDate='%s', modifiedDate='%s', firstName='%s', lastName='%s']",
                id,
                createdDate,
                modifiedDate,
                firstName,
                lastName
                );
    }
}
