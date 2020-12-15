package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SavingAccounts")
public class SavingAccount extends BankAccount {

    private static double maintainanceFeeNow = 50000;
    private static double interestNow = 0.3;

    @Getter
    @Setter
    private double interest;

    @Getter
    @Setter
    private double maintenanceFee;

    @PrePersist
    private void onCreate() {
        this.maintenanceFee = SavingAccount.maintainanceFeeNow;
        this.interest = SavingAccount.interestNow;
    }
}
