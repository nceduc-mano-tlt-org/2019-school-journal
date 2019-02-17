package ru.nceduc.journal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.service.GroupService;

@Controller
@RequestMapping("group/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupController {

    private final GroupService groupService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createGroup(Group group, String sectionId) {
        groupService.create(group, sectionId);
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGroup(Group group) {
        groupService.update(group);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(String id) {
        groupService.delete(id);
    }
}
