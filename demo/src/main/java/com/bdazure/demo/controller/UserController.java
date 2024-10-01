package main.java.com.bdazure.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import main.java.com.bdazure.demo.exception.UserNotFoundException;
import main.java.com.bdazure.demo.model.User;
import main.java.com.bdazure.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User postUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> removeUserById(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<User> searchAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public User searchUserById(@RequestParam Long id) {
        try {
            return userService.getUserById(id);
        } catch(UserNotFoundException userException) {
            return null;
        }
    }

    @PutMapping("update/{id}")
    public User changeUser(@PathVariable Long id, @RequestBody User newUser) {
        try {
            return userService.updateUser(id, newUser);
        } catch(UserNotFoundException userException) {
            return null;
        }
    }

    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            List<User> users = userService.getAllUsers();
            return "Connection success. Number of users: " + users.size();
        } catch (Exception e) {
            return "Error connecting to database: " + e.getMessage();
        }
    }
}
