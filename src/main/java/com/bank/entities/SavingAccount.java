package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SavingAccounts")
public class SavingAccount extends BankAccount {
    @Getter
    @Setter
    private double interest;

    @Getter
    @Setter
    private double maintenanceFee;
}
