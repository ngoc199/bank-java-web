package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Employees")
@RequiredArgsConstructor
public class Employee extends User {

    @Min(value = 1, message = "Level cannot be smaller than 1")
    @Max(value = 7, message = "Level cannot be greater than 7")
    @Getter
    @Setter
    private int level;

    @Min(value = 0, message = "Year of experience cannot be smaller than 0")
    @Getter
    @Setter
    private int yearOfExperience;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Department department;

    @Getter
    @Setter
    private double baseSalary;

    @Getter
    @Setter
    private double salary;
}
