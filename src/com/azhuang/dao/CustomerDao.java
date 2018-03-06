package com.azhuang.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.azhuang.domain.Customer;
import com.azhuang.domain.PageBean;

public interface CustomerDao {

	PageBean<Customer> findCustomerPage(int currPage , int pageSize , DetachedCriteria detachedCriteria) throws Exception;

	void save(Customer customer) throws Exception;
	
}
