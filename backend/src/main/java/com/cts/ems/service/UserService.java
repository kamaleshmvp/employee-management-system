package com.cts.ems.service;

import java.util.List;

import com.cts.ems.entity.User;

public interface UserService {

	public User saveUser(User user);
	public void deleteUser(Long userId);
	public List<User> getAllUsers();
}
