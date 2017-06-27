package com.hrd.spring.service;

import java.util.ArrayList;

import com.hrd.spring.model.Role;

public interface RoleService {
	
	public ArrayList<Role> getAllUsers();
	public boolean addUsers(Role role);

}
