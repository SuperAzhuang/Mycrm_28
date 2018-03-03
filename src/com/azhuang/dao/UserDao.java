package com.azhuang.dao;

import com.azhuang.domain.User;

public interface UserDao {

	User checkCode(String checkCode) throws Exception;

	void regist(User user) throws Exception;

	User login(User user) throws Exception;
}
