package com.hrd.spring.controller.rest;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrd.spring.model.User;
import com.hrd.spring.service.UserService;
import com.hrd.spring.storage.TempStorage;

@Controller
public class UserController {
	
	private String userHash;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping({ "/", "index", "/dashboard" })
	public String indexPage(ModelMap model) {
		model.addAttribute("LINK", "/");
		model.addAttribute("TITLE_HEADER", "Dashboard");
		model.addAttribute("SUB_TITLE_HEADER", "To manage web page");
		model.addAttribute("NUMOFUSER", userService.getAllUsers().size());
		model.addAttribute("TOTALMALE", userService.getTotalGender("M").size());
		model.addAttribute("TOTALFEMALE", userService.getTotalGender("F").size());
		model.addAttribute("NUMOFROLE", TempStorage.RoleStorage.size());
		return "dashboard";
	}

	@RequestMapping("/user-cu")
	public String userPage(ModelMap model) {
		model.addAttribute("LINK", "/user-cu");
		model.addAttribute("TITLE_HEADER", "Register User");
		model.addAttribute("SUB_TITLE_HEADER", "To register new user");
		model.addAttribute("USER", new User());
		return "user-cu";
	}

	@RequestMapping("/register")
	public String user(@ModelAttribute User user) {
		String userHash = UUID.randomUUID().toString();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		user.setCreated_date(timestamp);
		user.setUser_hash(userHash);
		user.setStatus("Y");
		userService.addUsers(user);
//		System.out.println(TempStorage.UserStorage.size());
		return "redirect:/user-cu";
	}

	@RequestMapping("/user-list")
	public String userList(ModelMap model) {
		model.addAttribute("TITLE_HEADER", "User List");
		model.addAttribute("SUB_TITLE_HEADER", "Show all user");
		model.addAttribute("USERLIST", userService.getAllUsers());
		return "user-list";
	}
	
	@RequestMapping("/update/{user_hash}")
	public String updateUser(@PathVariable("user_hash") String user_hash, ModelMap model) {
		model.addAttribute("TITLE_HEADER", "Update User Profile");
		model.addAttribute("SUB_TITLE_HEADER", "Update User Profile");
		model.addAttribute("USER", new User());
		userHash = user_hash;
		return "user-update";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute User user) {
		User u = userService.getUser(userHash);
		user.setUser_hash(userHash);
		user.setCreated_date(u.getCreated_date());
		user.setStatus("Y");
		
		if (user.getUsername() == "" || user.getUsername() == null) 
			user.setUsername(u.getUsername());
		if (user.getEmail() == "" || user.getEmail() == null) 
			user.setEmail(u.getEmail());
		if (user.getPhonenumber() == "" || user.getPhonenumber() == null) 
			user.setPhonenumber(u.getPhonenumber());
		if (user.getGender() == u.getGender())  
			user.setGender(u.getGender());
		userService.updateUser(user);
		
		return "redirect:/user-list";
	}
	
	@RequestMapping("/delete/{user_hash}")
	public String deleteUser(@PathVariable String user_hash) {
		userService.deleteUser(user_hash);
		return "redirect:/user-list";
	}
	
	@RequestMapping("/user-detail/{user_hash}")
	public String showUser(ModelMap model, @PathVariable String user_hash) {
		model.addAttribute("TITLE_HEADER", "User Profile");
		model.addAttribute("SUB_TITLE_HEADER", "Show Profile of User");
		model.addAttribute("USER", userService.getUser(user_hash));
		return "/user-profile";
	}

}