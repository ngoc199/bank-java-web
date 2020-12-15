package com.bank.controllers;

import java.util.List;
import java.util.Map;

import com.bank.entities.User;
import com.bank.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
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

    @PostMapping("/me")
    public User verifyUserLogin(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        return userService.validateUser(username, password);
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