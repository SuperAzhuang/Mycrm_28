package com.azhuang.serviceimpl;

import org.springframework.transaction.annotation.Transactional;

import com.azhuang.dao.UserDao;
import com.azhuang.domain.User;
import com.azhuang.service.UserService;
import com.azhuang.utils.MD5Utils;


@Transactional   //所有的方法开启事物
public class UserServiceImpl implements UserService{

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public User checkCode(String checkCode) throws Exception {
		// TODO Auto-generated method stub
		return userDao.checkCode(checkCode);
	}


	@Override
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		// 用户的状态默认是1状态
		user.setUser_state("1");
		userDao.regist(user);
	}


	@Override
	public User login(User user) throws Exception {
		// TODO Auto-generated method stub
		
		return 	userDao.login(user);
	}

}
