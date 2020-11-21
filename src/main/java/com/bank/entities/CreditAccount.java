package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CreditAccounts")
public class CreditAccount extends BankAccount {
    @Setter
    @Getter
    private double borrow;
}
