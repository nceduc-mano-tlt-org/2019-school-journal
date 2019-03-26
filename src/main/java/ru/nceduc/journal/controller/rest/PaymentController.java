package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nceduc.journal.dto.PaymentDTO;
import ru.nceduc.journal.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Payments operations", tags = "PAYMENT-V1")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/")
    @ApiOperation(value = "Get all payments")
    ResponseEntity<List<PaymentDTO>> getAll() {
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-student/{studentId}")
    @ApiOperation(value = "Get all payments by student id")
    ResponseEntity<List<PaymentDTO>> getAll(@PathVariable String studentId) {
        return new ResponseEntity<>(paymentService.getAllByStudentId(studentId), HttpStatus.OK);
    }

}
