package com.exam.repository;

import com.exam.entity.User;
import com.exam.exceptions.CalculatorAuthException;

public interface UserRepository {
    Integer create(String email, String password) throws CalculatorAuthException;
    User findByEmailAndPassword(String email, String password) throws CalculatorAuthException;
    Integer getCountByEmail(String email);
    User findById(Integer userId);
    Double getBalance(Integer userId);
    void discountCost(Double costOfOperation, Integer userId);
}
