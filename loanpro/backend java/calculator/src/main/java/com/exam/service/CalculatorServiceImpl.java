package com.exam.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import com.exam.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import com.exam.repository.RecordRepository;
import com.exam.repository.UserRepository;
import com.exam.repository.OperationRepository;

import java.lang.Math; 

import java.net.http.HttpClient;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CalculatorServiceImpl implements CalculatorService{

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public Double getRootSquare(Double[] inputData) { 
        if (inputData == null) {
            return  null;
        }
        double rootSquare = Math.sqrt(Arrays.stream(inputData).sorted(Comparator.reverseOrder()).limit(3).map(x->x*x).reduce(0.0,Double::sum));
        return Math.round(rootSquare * 100.0) / 100.0;
    }

    @Override
    public String newOperationResult(String operation, Integer firstNumber, Integer secondNumber, Integer userId) { 

        String resp = "";

        Double costOfOperation = operationRepository.getCost(operation);
        Double currentUserBalance = userRepository.getBalance(userId);
        if (costOfOperation != currentUserBalance){
            recordRepository.createRecord(operation,userId, currentUserBalance, costOfOperation);
            userRepository.discountCost(costOfOperation, userId);
        }
        
        switch (operation) {
        case "addition":
            System.out.println(firstNumber + secondNumber);
            resp = String.valueOf(firstNumber + secondNumber);
            break;
        case "subtraction":
            System.out.println(firstNumber - secondNumber);
            resp = String.valueOf(firstNumber - secondNumber);
            break;
        case "multiplication":
            System.out.println(firstNumber * secondNumber);
            resp = String.valueOf(firstNumber * secondNumber);
            break;
        case "division":
            if (secondNumber != 0)
                System.out.println(firstNumber / secondNumber);
                resp = String.valueOf(firstNumber / secondNumber);
            break;
        case "square_root":
            System.out.println(Math.sqrt(firstNumber));
            resp = String.valueOf(Math.sqrt(firstNumber));
            break;
        case "random_string":
            System.out.println(randomAPI());
            resp = String.valueOf(randomAPI());
            break;
        default:
            System.out.println("Bad Operation");        
        }

        return resp;   
    }

    private String randomAPI(){
        String resp = "";
        String uri = "https://www.random.org/strings/?num=10&len=10&digits=on&unique=on&format=html&rnd=new";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();
        HttpResponse<String> response = null;
        try{
            response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        String responseBody = response.body();
        int start = responseBody.indexOf("<pre class=\"data\">")+"<pre class=\"data\">".length();
        int end = responseBody.lastIndexOf("</pre>");

        resp = responseBody.substring(start, end);

        return resp;
    }
}



