package com.bank.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customers")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.PERSIST)
    @Size(max = 3)
    @Getter
    @Setter
    private List<SavingAccount> listSavingAccounts;

    @OneToMany(cascade = CascadeType.PERSIST)
    @Size(max = 2)
    @Getter
    @Setter
    private List<CreditAccount> listCreditAccounts;
}
