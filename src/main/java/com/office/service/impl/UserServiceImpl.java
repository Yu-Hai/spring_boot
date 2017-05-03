package com.office.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.office.entity.User;
import com.office.mapper.UserMapper;
import com.office.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper UserMapper;
	
	@Override
	public int insertUserWithBackId(User user) {
		
		return 0;
	}

}
