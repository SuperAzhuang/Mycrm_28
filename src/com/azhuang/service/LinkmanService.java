package com.azhuang.service;

import org.hibernate.criterion.DetachedCriteria;

import com.azhuang.domain.Customer;
import com.azhuang.domain.Linkman;
import com.azhuang.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer currPage, Integer pageSize, DetachedCriteria detachedCriteria)
			throws Exception;

	void add(Linkman linkman) throws Exception;

}
