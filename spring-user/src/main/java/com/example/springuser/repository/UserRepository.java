package com.example.springuser.repository;

import java.util.List;

import com.example.springuser.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByAge(int age);
}
