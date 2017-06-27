package com.hrd.spring.repository;

import java.util.ArrayList;

import com.hrd.spring.model.Role;

public interface RoleRepository {
	
	public ArrayList<Role> getAllUsers();
	public boolean addUsers(Role role);

}
