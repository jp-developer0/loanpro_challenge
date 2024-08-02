package com.exam.repository;

import com.exam.exceptions.CalculatorAuthException;


public interface OperationRepository {
    Double getCost(String typeOfOperation) throws CalculatorAuthException;
}
