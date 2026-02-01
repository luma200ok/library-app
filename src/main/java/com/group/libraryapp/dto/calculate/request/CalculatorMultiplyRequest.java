package com.group.libraryapp.dto.calculate.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CalculatorMultiplyRequest {

    private final int num1;
    private final int num2;
}
