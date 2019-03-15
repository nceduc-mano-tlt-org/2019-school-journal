package ru.nceduc.journal.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private String id;
    private String name;
    private String description;
    private String userId;
}
