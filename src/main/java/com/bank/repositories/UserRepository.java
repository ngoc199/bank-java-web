package com.bank.repositories;

import java.util.Optional;

import com.bank.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

    public User[] findByName(String name);
}
