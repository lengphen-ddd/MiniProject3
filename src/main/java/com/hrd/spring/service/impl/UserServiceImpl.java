package com.hrd.spring.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hrd.spring.model.User;
import com.hrd.spring.repository.UserRepository;
import com.hrd.spring.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository ;

	@Override
	public ArrayList<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public boolean addUsers(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean deleteUser(String user_hash) {
		return userRepository.deleteUser(user_hash);
	}

	@Override
	public User getUser(String user_hash) {
		return userRepository.getUser(user_hash);
	}

	@Override
	public ArrayList<User> getTotalGender(String gender) {
		 return userRepository.getTotalGender(gender);
	}
	
}
