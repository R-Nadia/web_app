package com.example.springuser.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springuser.model.User;
import com.example.springuser.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Get all Users...");

        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);

        return users;
    }

    @PostMapping(value = "/users/create")
    public User postUser(@RequestBody User user) {

        User _user = repository.save(new User(user.getName(), user.getAge()));
        return _user;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        System.out.println("Delete User with ID = " + id + "...");

        repository.deleteById(id);

        return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
    }

    @DeleteMapping("/users/delete")
    public ResponseEntity<String> deleteAllUsers() {
        System.out.println("Delete All Users...");

        repository.deleteAll();

        return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
    }

    @GetMapping(value = "users/age/{age}")
    public List<User> findByAge(@PathVariable int age) {

        List<User> users = repository.findByAge(age);
        return users;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Update User with ID = " + id + "...");

        Optional<User> userData = repository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setName(user.getName());
            _user.setAge(user.getAge());
            _user.setActive(user.isActive());
            return new ResponseEntity<>(repository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
