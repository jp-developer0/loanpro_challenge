package com.exam.controller;

import com.exam.entity.RequestData;
import com.exam.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import com.exam.service.RecordService;

@Slf4j
@RestController
@RequestMapping(value="/api/v1/calculator",produces = { MediaType.APPLICATION_JSON_VALUE })
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/rootsquare")
    public ResponseEntity<Map<String, Double>> getSquareRoot(@RequestBody RequestData requestData) {
        try {
            Double rootSquareValue = calculatorService.getRootSquare(requestData.getData());
            Map<String, Double> responseMap = new HashMap<>();
            responseMap.put("output",rootSquareValue);
            log.info("Root square : " + rootSquareValue);
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error occurring while getting square root " + ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/operation")
    public ResponseEntity<Map<String, String>> getSquareRoot(@RequestBody Map<String,Object> userMap) {
        try {
            String operation = String.valueOf(userMap.get("operation"));
            Integer firstNumber = Integer.valueOf(String.valueOf(userMap.get("firstNumber")));
            Integer secondNumber = Integer.valueOf(String.valueOf(userMap.get("secondNumber")));
            Integer userId = Integer.valueOf(String.valueOf(userMap.get("userId")));
            String newOperationResult = calculatorService.newOperationResult(operation, firstNumber, secondNumber, userId);
            Map<String,String> responseMap = new HashMap<>();
            responseMap.put("message","Operation " + operation + " done successfully with user ID: " + userId + " with result " + newOperationResult);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error occurring while getting square root " + ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}

//[Ljava.lang.Double;@16c5faa
