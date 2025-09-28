package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
