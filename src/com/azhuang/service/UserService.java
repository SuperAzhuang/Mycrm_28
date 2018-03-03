package com.azhuang.service;

import com.azhuang.domain.User;

public interface UserService {

	User checkCode(String checkCode) throws Exception;

	void regist(User user) throws Exception;

	User login(User user) throws Exception;
	
}
