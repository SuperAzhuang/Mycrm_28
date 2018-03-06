package com.azhuang.daoimpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.azhuang.dao.CustomerDao;
import com.azhuang.domain.Customer;
import com.azhuang.domain.PageBean;

public class CustomerDaoimpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public PageBean<Customer> findCustomerPage(int currPage, int pageSize, DetachedCriteria detachedCriteria)
			throws Exception {
		// TODO Auto-generated method stub

		// 先查询总记录数 select count(*)
		detachedCriteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		int totalCount = 0;
		if (list != null && list.size() > 0) {
			totalCount = list.get(0).intValue();
			// 总的记录数
			// page.setTotalCount(totalCount);
		}
		detachedCriteria.setProjection(null);
		System.out.println("totalCount = " + totalCount);
		List<Customer> lists = (List<Customer>) getHibernateTemplate().findByCriteria(detachedCriteria,
				(currPage - 1) * pageSize, pageSize);

		for (Customer customer : lists) {
			System.out.println("customer = " + customer);

		}

		int totalPage = (int) Math.ceil((double) totalCount / pageSize);

		PageBean<Customer> pageBean = new PageBean<Customer>(lists, currPage, pageSize, totalCount, totalPage);

		return pageBean;
	}

	@Override
	public void save(Customer customer) throws Exception {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(customer);
	}

}
