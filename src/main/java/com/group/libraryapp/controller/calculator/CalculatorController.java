package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculate.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculate.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @GetMapping("/add")
    public int addToNumbers(CalculatorAddRequest request) {
        return request.getNum1()+ request.getNum2();
    }

    @PostMapping("/multiply")
    public int multiplyToNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNum1() * request.getNum2();
    }
}
