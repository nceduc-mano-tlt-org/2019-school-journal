package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Groups operations", tags = "GROUP-V1")
public class GroupController {

    private final GroupService groupService;

    @ApiOperation(value = "Get details of specific group")
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable String id) {
        GroupDTO groupDTO = groupService.get(id);
        return new ResponseEntity<>(groupDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all groups")
    @GetMapping("/")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groupsDTO = groupService.getAll();
        return new ResponseEntity<>(groupsDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all groups by section id")
    @GetMapping("/by-section/{sectionId}")
    public ResponseEntity<List<GroupDTO>> getGroupsBySectionId(@PathVariable String sectionId) {
        List<GroupDTO> groupsDTO = groupService.getAllBySectionId(sectionId);
        return new ResponseEntity<>(groupsDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new group")
    @PostMapping("/")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO createdGroup = groupService.create(groupDTO);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update group details")
    @PutMapping("/")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO updatedGroup = groupService.update(groupDTO);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @ApiOperation(value = "Patch group details")
    @PatchMapping("/")
    public ResponseEntity<GroupDTO> patchGroup(@RequestBody GroupDTO groupDTO) {
        GroupDTO patchedGroup = groupService.patch(groupDTO);
        return new ResponseEntity<>(patchedGroup, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a group")
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDTO> deleteGroup(@PathVariable String id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
