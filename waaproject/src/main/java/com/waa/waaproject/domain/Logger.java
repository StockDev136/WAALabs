package com.waa.waaproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long transactionId;
    LocalDate Date;
    LocalTime Time;
    String Principle;
    String Operation;

    public Logger() {
    }

    public Logger(LocalDate date, LocalTime time, String principle, String operation) {
        Date = date;
        Time = time;
        Principle = principle;
        Operation = operation;
    }
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    public LocalDate getDate() {
        return Date;
    }
    public void setDate(LocalDate date) {
        Date = date;
    }
    public LocalTime getTime() {
        return Time;
    }
    public void setTime(LocalTime time) {
        Time = time;
    }
}
