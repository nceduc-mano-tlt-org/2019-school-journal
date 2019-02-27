package ru.nceduc.journal.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private String id;
    private String nameProject;

    public ProjectDTO() {
    }
    public ProjectDTO(String nameProject) {
        this.nameProject = nameProject;
    }
}
