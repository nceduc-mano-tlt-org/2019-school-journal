package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.GroupDTO;
import ru.nceduc.journal.entity.Group;
import ru.nceduc.journal.service.GroupService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description="Groups operations", tags = "GROUP-V1")
public class GroupController {

    private final GroupService groupService;

    @ApiOperation(value = "Get details of specific group")
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroup(@PathVariable String id) {
        Optional<GroupDTO> optionalDTO = groupService.get(id);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @ApiOperation(value = "Get all groups")
    @GetMapping("/")
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all groups by section id")
    @GetMapping("/by-section/{sectionId}")
    public ResponseEntity<List<GroupDTO>> getGroupsBySectionId(@PathVariable String sectionId) {
        return new ResponseEntity<>(groupService.getAllBySectionId(sectionId), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new group")
    @PostMapping("/")
    public ResponseEntity<GroupDTO> createGroup(@RequestBody GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = groupService.create(groupDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Update group details")
    @PutMapping("/")
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = groupService.update(groupDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Patch group details")
    @PatchMapping("/")
    public ResponseEntity<GroupDTO> patchGroup(@RequestBody GroupDTO groupDTO) {
        Optional<GroupDTO> optionalDTO = groupService.patch(groupDTO);
        return optionalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @ApiOperation(value = "Delete a group")
    @DeleteMapping("/{id}")
    public ResponseEntity<GroupDTO> deleteGroup(@PathVariable String id) {
        groupService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
