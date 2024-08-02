package com.exam.repository;

import com.exam.entity.Record;
import com.exam.exceptions.CalculatorAuthException;

public interface RecordRepository {
    Integer createRecord(String typeOfOperation,Integer userId, Double balanceUser, Double cost) throws CalculatorAuthException;
}
