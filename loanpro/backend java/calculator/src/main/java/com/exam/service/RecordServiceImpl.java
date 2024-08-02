package com.exam.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import com.exam.entity.User;
import com.exam.exceptions.CalculatorAuthException;

import com.exam.repository.RecordRepository;

@Service
public class RecordServiceImpl {

    RecordRepository recordRepository;
/*
    @Override
    public Record setRecord(User user, Double cost) throws CalculatorAuthException {
        
        Integer recordId = recordRepository.create(user.getUserId(),cost);

        return recordRepository.findById(recordId);
    }
    */
}
