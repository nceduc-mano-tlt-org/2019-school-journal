package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.DepositDTO;
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

    @PutMapping("/deposit/")
    @ApiOperation(value = "Add certain amount of money to the student's account")
    ResponseEntity deposit(@RequestBody DepositDTO depositDTO) {
        HttpStatus httpStatus = paymentService.deposit(depositDTO) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(httpStatus);
    }

    @PutMapping("/withdraw/")
    @ApiOperation(value = "Write off certain amount of money to the student's account")
    ResponseEntity withdraw(@RequestBody DepositDTO depositDTO) {
        HttpStatus httpStatus = paymentService.withdraw(depositDTO) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(httpStatus);
    }
}
