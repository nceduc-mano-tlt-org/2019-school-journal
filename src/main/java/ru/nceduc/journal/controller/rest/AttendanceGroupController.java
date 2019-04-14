package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nceduc.journal.service.AttendanceGroupService;

@RestController
@RequestMapping("api/v1/group/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance of groups in School Journal", tags = "ATTENDANCE-GROUP-V1")
public class AttendanceGroupController {
    private final AttendanceGroupService attendanceService;
}
