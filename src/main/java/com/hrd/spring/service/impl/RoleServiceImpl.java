package com.hrd.spring.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hrd.spring.model.Role;
import com.hrd.spring.repository.RoleRepository;
import com.hrd.spring.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	@Qualifier("roleRepository")
	private RoleRepository roleRepository;

	@Override
	public ArrayList<Role> getAllUsers() {
		return roleRepository.getAllUsers();
	}

	@Override
	public boolean addUsers(Role role) {
		return roleRepository.addUsers(role);
	}

}
