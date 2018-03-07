package com.azhuang.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.Registration;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.azhuang.domain.Customer;
import com.azhuang.domain.PageBean;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;

	public BaseDaoImpl() {
		// this表示的子类，c表示就是CustomerDaoImpl的Class对象
		Class c = this.getClass();
		// CustomerDaoImpl extends BaseDaoImpl<Customer> map<k,v>
		// 第2步：获取到是BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();

		// 目的：把type接口转换成子接口
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;

			// 获取到 Customer
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];

		}
		// System.out.println(
		// "clazz = " + clazz.getSimpleName() + " --clazz=getName = " + clazz.getName()
		// + " clazz =" + clazz);
	}

	@Override
	public void save(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
	}

	@Override
	public T findById(Long id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().get(clazz, id);
		return null;
	}

	@Override
	public List<T> findAll() {
		getHibernateTemplate().find("from " + clazz.getSimpleName());
		return null;
	}

	@Override
	public PageBean<T> findByPage(Integer currPage, Integer pageSize, DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		// 设置查询聚合函数：SQL已经变成了 select count(*) from
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		int totalCount = 0;
		if (list != null && list.size() > 0) {
			totalCount = list.get(0).intValue();
			// 总记录数

		}
		int totalPage = (int) Math.ceil((double) totalCount / pageSize);
		// 清除SQL select * from xxx
		criteria.setProjection(null);
		List<T> lists = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (currPage - 1) * pageSize,
				pageSize);

		// new PageBean<>(lists, currPage, pageSize, totalCount, totalPage);

		return new PageBean<>(lists, currPage, pageSize, totalCount, totalPage);
	}

}
