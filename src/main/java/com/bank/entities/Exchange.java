package com.bank.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Table(name = "Exchanges")
@Data
@RequiredArgsConstructor
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private final BankAccount sender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private final BankAccount receiver;
    private final double amount;
}
