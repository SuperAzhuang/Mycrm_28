package com.azhuang.serviceimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import com.azhuang.dao.CustomerDao;
import com.azhuang.domain.Customer;
import com.azhuang.domain.PageBean;
import com.azhuang.service.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public PageBean<Customer> findCustomerPage(int currPage, int pageSize, DetachedCriteria detachedCriteria)
			throws Exception {
		// TODO Auto-generated method stub
		
		PageBean<Customer> pageBean = customerDao.findCustomerPage(currPage, pageSize, detachedCriteria);
		
		return pageBean;
	}

	@Override
	public void save(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		customerDao.save(customer);
	}

}
