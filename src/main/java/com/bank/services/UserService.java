package com.bank.services;

import java.util.List;

import javax.transaction.Transactional;

import com.bank.entities.User;
import com.bank.exceptions.UserNotFoundException;
import com.bank.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find all users in the database
     * 
     * @return listUsers
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Delete user by id
     * 
     * @param id
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    /**
     * Validate user login
     * 
     * @param username
     * @param password
     * @return validateResult
     */
    public User validateUser(String username, String password) {
        return userRepository.findByUsername(username).map(user -> {
            if (user.getPassword().equals(password)) {
                return user;
            }
            return null;
        }).orElse(null);
    }
}
