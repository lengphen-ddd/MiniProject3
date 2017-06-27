package com.hrd.spring.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.hrd.spring.model.User;

@Repository
public interface UserRepository {
	
	
	@Select("SELECT gender FROM users WHERE gender = #{gender}")
	public ArrayList<User> getTotalGender(String gender);
	
	
	@Select("SELECT id,"
			+ " username,"
			+ " email,"
			+ " gender,"
			+ " phonenumber,"
			+ " status,"
			+ " created_date"
			+ " FROM users"
			+ " WHERE user_hash=#{user_hash}")
	public User getUser(String user_hash);

	@Select("SELECT id,"
			+ " username,"
			+ " email,"
			+ " gender,"
			+ " phonenumber,"
			+ " status,"
			+ " user_hash,"
			+ " created_date"
			+ " FROM users ORDER BY 1")
	public ArrayList<User> getAllUsers();

	@Insert("INSERT INTO users(username,"
			+ " email,"
			+ " gender,"
			+ " phonenumber,"
			+ " status,"
			+ " user_hash,"
			+ " created_date)"
			+ " VALUES (#{user.username},"
			+ " #{user.email},"
			+ " #{user.gender},"
			+ " #{user.phonenumber},"
			+ " #{user.status},"
			+ " #{user.user_hash},"
			+ " #{user.created_date}"
			+ ")")
	public boolean saveUser(@Param("user") User user);

	@Update("UPDATE users SET"
			+ " username=#{user.username},"
			+ " email=#{user.email},"
			+ " gender=#{user.gender},"
			+ " phonenumber=#{user.phonenumber},"
			+ " status=#{user.status},"
			+ " created_date=#{user.created_date}"
			+ " WHERE user_hash=#{user.user_hash}")
	public boolean updateUser(@Param("user") User user);
	
	@Delete("DELETE FROM users WHERE user_hash=#{user_hash}")
	public boolean deleteUser(String user_hash);

}
