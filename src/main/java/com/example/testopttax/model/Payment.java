package com.example.testopttax.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Payment extends Model {
    private Float amount;
    private String type;
    private Float balance;
    private Integer age;
    private LocalTime time;
    private String coordinates;
}
