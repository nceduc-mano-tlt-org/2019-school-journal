package ru.nceduc.journal.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nceduc.journal.dto.DepositDTO;
import ru.nceduc.journal.dto.PaymentDTO;
import ru.nceduc.journal.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(description = "Operations pertaining to payments", tags = "PAYMENT-V1")
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PostMapping("/transfer/")
    @ApiOperation(value = "Transfer certain amount of money")
    ResponseEntity transfer(@RequestBody PaymentDTO paymentDTO) {
        HttpStatus httpStatus = paymentService.transfer(paymentDTO) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(httpStatus);
    }

    @DeleteMapping("/cancel/{paymentId}")
    @ApiOperation(value = "Cancel a payment and return money to the student's account")
    ResponseEntity cancel(@PathVariable String paymentId) {
        HttpStatus httpStatus = paymentService.cancel(paymentId) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity(httpStatus);
    }

}
