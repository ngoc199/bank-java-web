package com.bank.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer extends User {

}
