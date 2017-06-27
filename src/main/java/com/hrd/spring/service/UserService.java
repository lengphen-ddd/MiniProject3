package com.hrd.spring.service;

import java.util.ArrayList;

import com.hrd.spring.model.User;

public interface UserService {
	
	public ArrayList<User> getAllUsers();
	public boolean addUsers(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(String user_hash);
	public User getUser(String user_hash);
	public ArrayList<User> getTotalGender(String gender);
	
}
