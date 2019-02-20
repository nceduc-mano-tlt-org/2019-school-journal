package ru.nceduc.journal.dto;

import lombok.Data;

@Data
public class GroupDTO {
    private String id;
    private String name;
    private String description;
    private String sectionId;
}
