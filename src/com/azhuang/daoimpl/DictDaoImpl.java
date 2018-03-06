package com.azhuang.daoimpl;

import java.util.List;

import javax.persistence.criteria.From;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.azhuang.dao.DictDao;
import com.azhuang.domain.Dict;

public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	@Override
	public List<Dict> findDictByCode(String codeType) throws Exception {
		// TODO Auto-generated method stub

		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dict.class);

		detachedCriteria.add(Restrictions.eq("dict_type_code", codeType));

		List<Dict> findByCriteria = (List<Dict>) getHibernateTemplate().findByCriteria(detachedCriteria);

		List<Dict> lists = (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?",
				codeType);

//		for (Dict dict : lists) {
//			System.out.println("dict ---  " + dict + " findByCriteria = size = " + findByCriteria.size());
//		}

		return lists;
	}

}
