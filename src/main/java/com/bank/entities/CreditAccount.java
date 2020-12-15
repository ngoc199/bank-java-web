package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CreditAccounts")
public class CreditAccount extends BankAccount {

    private static double borrowNow = 10000000;

    @Setter
    @Getter
    private double borrow;

    @Getter
    @Setter
    private double debt;

    @PrePersist
    private void onCreate() {
        this.borrow = CreditAccount.borrowNow;
        this.setBalance(this.borrow);
    }
}
