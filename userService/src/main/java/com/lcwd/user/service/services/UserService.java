package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;
import java.util.List;

public interface UserService {

	
	//create
	User savUser(User user);
	
	//getAllUSer
	List<User> getAllUser();
	
	//get single user of given id
	User getUser(String userId);
	
	//TODO delete,update
}