package com.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  
import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer operationId;
    @Column
    private Integer userId;
    @Column
    private Double amount;
    @Column
    private Double userBalance;
    @Column
    private String operationResponse;
    @Column
    private String date;
}
