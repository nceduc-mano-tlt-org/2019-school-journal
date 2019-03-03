package ru.nceduc.journal.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.service.GroupService;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> createGroup(GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.create(groupDTO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<GroupDTO> patchGroup(GroupDTO groupDTO) {
        GroupDTO patchedGroup = groupService.patch(groupDTO);
        return new ResponseEntity<>(patchedGroup, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GroupDTO> updateGroup(GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.update(groupDTO);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GroupDTO> deleteGroup(@PathVariable String id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
