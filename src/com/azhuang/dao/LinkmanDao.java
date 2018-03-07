package com.azhuang.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.azhuang.domain.Linkman;
import com.azhuang.domain.PageBean;
import com.azhuang.domain.User;

public interface LinkmanDao {


	PageBean<Linkman> findByPage(Integer currPage, Integer pageSize, DetachedCriteria detachedCriteria)
			throws Exception;

	void add(Linkman linkman) throws Exception;

}
