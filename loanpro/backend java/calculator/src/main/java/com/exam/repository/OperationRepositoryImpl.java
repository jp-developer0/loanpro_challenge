package com.exam.repository;

import com.exam.exceptions.CalculatorAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
public class OperationRepositoryImpl implements OperationRepository{
    private static final String SQL_GET = "SELECT cost FROM OPERATIONS WHERE type = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Double getCost(String typeOfOperation) throws CalculatorAuthException {
        try {
            Double cost = jdbcTemplate.queryForObject(SQL_GET, new Object[] {typeOfOperation}, costRowMapper);
            return cost;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw new CalculatorAuthException("Invalid details. Failed to create account");
        }
    }

    private RowMapper<Double> costRowMapper = ((rs, rowNum) -> {
        return new Double(rs.getInt("cost"));
    });

}
