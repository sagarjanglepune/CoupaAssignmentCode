package com.example.assignment.repository;

import com.example.assignment.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    public Login findOneByUsername(String username);
}