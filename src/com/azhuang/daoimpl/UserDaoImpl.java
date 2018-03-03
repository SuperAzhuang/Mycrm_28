package com.azhuang.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.azhuang.dao.UserDao;
import com.azhuang.domain.User;
import com.azhuang.utils.MD5Utils;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User checkCode(String checkCode) throws Exception {
		// TODO Auto-generated method stub
		List<User> lists = (List<User>) getHibernateTemplate().find("from User where user_code  = ?", checkCode);

		if (lists != null && lists.size() > 0) {

			return lists.get(0);

		}
		return null;
	}

	@Override
	public void regist(User user) throws Exception {
		// TODO Auto-generated method stub
		// 往数据库添加数据
		getHibernateTemplate().save(user);
	}

	@Override
	public User login(User user) throws Exception {

		// 拼接查询的条件
	
		// TODO Auto-generated method stub
		// QBC的查询，按条件进行查询
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		System.out.println("login  user  = " +user);
		
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", MD5Utils.md5(user.getUser_password())));
	//	criteria.add(Restrictions.eq("user_state", "1"));

		List<User> list = (List<User>) getHibernateTemplate().findByCriteria(criteria);
		if (list!=null && list.size()>0) {
			
			return list.get(0);
		}
		
		
		return null;
	}

}
