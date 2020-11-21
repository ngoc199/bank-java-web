package com.bank.services;

import java.util.List;

import com.bank.entities.User;
import com.bank.payroll.UserNotFoundException;
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
     * Find user by id
     * 
     * @param id
     * @return user
     */
    public User findUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Save user to the database
     * 
     * @param user
     * @return user
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(long id, User updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    /**
     * Delete user by id
     * 
     * @param id
     */
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
    public boolean isValidatedUser(String username, String password) {
        User user = userRepository.findByUsername(username).get();
        return user.getPassword().equals(password);
    }
}
