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
public class UserRepositoryImpl implements UserRepository{
    private static final String SQL_CREATE = "INSERT INTO USERS(USER_ID, EMAIL, PASSWORD, STATUS, BALANCE) VALUES(NEXTVAL('USERS_SEQ'), ?, ?, ?, ?)";
    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_BY_ID = "SELECT USER_ID, EMAIL, PASSWORD, STATUS FROM USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, EMAIL, PASSWORD, STATUS FROM USERS WHERE EMAIL = ?";
    private static final String SQL_FIND_AMOUNT_BY_ID = "SELECT BALANCE FROM USERS WHERE user_id = ?";
    private static final String SQL_UPDATE_AMOUNT = "UPDATE USERS SET BALANCE = ? WHERE user_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String email, String password) throws CalculatorAuthException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            String encryptedPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                ps.setString(2, encryptedPassword);
                ps.setString(3, "active");
                ps.setDouble(4, 888.0);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception ex) {
            throw new CalculatorAuthException("Invalid details. Failed to create account");
        }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws CalculatorAuthException {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] {email}, userRowMapper);
            if (!BCrypt.checkpw(password,user.getPassword())) {
                throw new CalculatorAuthException("Invalid email or password");
            }
            return  user;
        } catch (EmptyResultDataAccessException ex) {
            throw new CalculatorAuthException("Invalid email or password");
        }
    }

    @Override
    public void discountCost(Double costOfOperation, Integer userId){
        Double actualUserBalance = getBalance(userId);
        jdbcTemplate.update(
                SQL_UPDATE_AMOUNT, 
                actualUserBalance-costOfOperation, userId);
    }


    @Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {userId}, userRowMapper);
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("STATUS"));
    });

    @Override
    public Double getBalance(Integer userId){
        return jdbcTemplate.queryForObject(SQL_FIND_AMOUNT_BY_ID, new Object[] {userId}, Double.class);
    }
}
