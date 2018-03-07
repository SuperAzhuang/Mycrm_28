package com.azhuang.daoimpl;

import org.hibernate.criterion.DetachedCriteria;

import com.azhuang.domain.Linkman;
import com.azhuang.domain.PageBean;
import com.azhuang.service.LinkmanService;

public class LinkmanServiceImpl implements LinkmanService {

	
	 
	
	@Override
	public PageBean<Linkman> findByPage(Integer currPage, Integer pageSize, DetachedCriteria detachedCriteria)
			throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public void add(Linkman linkman) throws Exception {
		// TODO Auto-generated method stub

	}

}
