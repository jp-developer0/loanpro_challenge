package com.exam.repository;

import com.exam.entity.User;
import com.exam.exceptions.CalculatorAuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
public class RecordRepositoryImpl implements RecordRepository{
    private static final String SQL_CREATE_RECORD  = "INSERT INTO RECORDS(RECORD_ID, TYPE_OPERATION, USER_ID, AMOUNT, USER_BALANCE, OPERATION_RESPONSE, FECHA) VALUES(NEXTVAL('USERS_SEQ'), ?, ?, ?, ?, ?, ?)";
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer createRecord(String typeOfOperation,Integer userId, Double balanceUser, Double cost) throws CalculatorAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_RECORD, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, typeOfOperation);
                ps.setInt(2, userId);
                ps.setDouble(3, cost);
                ps.setDouble(4, balanceUser - cost);
                ps.setString(5, "Good");
                ps.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("RECORD_ID");
        } catch (Exception ex) {
            throw new CalculatorAuthException("Invalid details. Failed to create account");
        }
    }
}
