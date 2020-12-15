package com.bank.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "BankAccounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class BankAccount {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID accountId;
    private BankAccountType bankAccountType;
    private double balance;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

}
