package com.azhuang.serviceimpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.azhuang.dao.DictDao;
import com.azhuang.domain.Dict;
import com.azhuang.service.DictService;


@Transactional
public class DictServiceImpl  implements DictService{

	private DictDao dictDao ;
	

	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}


	@Override
	public List<Dict> findDictByCode(String codeType) throws Exception {
		// TODO Auto-generated method stub
		List<Dict> lists = dictDao.findDictByCode(codeType);
		
		return lists;
	}

}
