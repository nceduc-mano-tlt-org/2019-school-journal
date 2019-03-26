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
    
}
