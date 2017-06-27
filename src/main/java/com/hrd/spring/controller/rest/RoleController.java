package com.hrd.spring.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hrd.spring.model.Role;
import com.hrd.spring.service.RoleService;
import com.hrd.spring.storage.TempStorage;

@Controller
public class RoleController {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@RequestMapping("/role-cu")
	public String rolePage(ModelMap model) {
		model.addAttribute("LINK", "/role-cu");
		model.addAttribute("TITLE_HEADER", "Add Role");
		model.addAttribute("SUB_TITLE_HEADER", "To Add new role");
		model.addAttribute("ROLE", new Role());
		return "role-cu";
	}
	
	@RequestMapping("/add-role")
	public String addRole(@ModelAttribute Role role) {
		roleService.addUsers(role);
		return "redirect:/role-cu";
	}
	
	@RequestMapping("/role-list")
	public String roleList(ModelMap model) {
		model.addAttribute("TITLE_HEADER", "Role List");
		model.addAttribute("SUB_TITLE_HEADER", "Show all role for user");
		model.addAttribute("ROLELIST", TempStorage.RoleStorage);
		return "role-list";
	}
	
}
