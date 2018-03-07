package com.azhuang.dao;

import org.hibernate.criterion.DetachedCriteria;

import com.azhuang.domain.Customer;
import com.azhuang.domain.PageBean;

public interface CustomerDao {

	PageBean<Customer> findCustomerPage(int currPage , int pageSize , DetachedCriteria detachedCriteria) throws Exception;

	void save(Customer customer) throws Exception;

	Customer findById(Long cust_id) throws Exception;

	void updateCustomer(Customer customer) throws Exception;

	void deleteCoustomer(Customer customer) throws Exception;
	
}
