package com.cts.ems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.ems.entity.User;
import com.cts.ems.exception.UserExistException;
import com.cts.ems.exception.UserNotFoundException;
import com.cts.ems.repository.UserRepository;
import com.cts.ems.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		
		if(userRepository.existsByUserName(user.getUserName())) {
			throw new UserExistException("Username already exists");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long userId) {
		
		if(!userRepository.existsById(userId)) {
			throw new UserNotFoundException("User with Id " + userId + " does not exist");
		}
		
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users = userRepository.findAll();
		return users;
	}

}
