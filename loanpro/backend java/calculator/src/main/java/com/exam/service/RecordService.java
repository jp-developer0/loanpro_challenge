package com.exam.service;

import org.springframework.stereotype.Service;
import com.exam.entity.User;
import com.exam.exceptions.CalculatorAuthException;
import com.exam.entity.Record;


public interface RecordService {
    Record setRecord(User user, Double cost) throws CalculatorAuthException;
}
