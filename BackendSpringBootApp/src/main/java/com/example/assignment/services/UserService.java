package com.example.assignment.services;

import com.example.assignment.model.Login;
import com.example.assignment.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	LoginRepository loginRepository;

	public Login save(Login user) {

		return loginRepository.saveAndFlush(user);
	}

	public Login find(String userName) {

		return loginRepository.findOneByUsername(userName);
	}
}