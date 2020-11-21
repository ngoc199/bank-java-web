package com.bank.controllers;

import java.util.List;

import javax.validation.Valid;

import com.bank.entities.User;
import com.bank.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Find all user in the database
     * 
     * @return listUsers
     */
    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    /**
     * Create new user
     * 
     * @param user
     * @return user
     */
    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Find user by id
     * 
     * @param id
     * @return user
     */
    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    /**
     * Update user by id
     * 
     * @param id
     * @param updatedUser
     * @return updatedUser
     */
    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") long id, @Valid @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    /**
     * Delete user by id
     * 
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteUserById(id);
    }
}