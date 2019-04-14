package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student/attendance")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to attendance of students in School Journal", tags = "ATTENDANCE-STUDENT-V1")
public class AttendanceStudentController {
}
