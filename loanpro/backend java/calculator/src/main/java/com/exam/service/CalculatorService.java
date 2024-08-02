package com.exam.service;

import org.springframework.stereotype.Service;

public interface CalculatorService {
    Double getRootSquare(Double[] inputData);

    String newOperationResult(String operation, Integer firstNumber, Integer secondNumber, Integer userId);
}
