package com.azhuang.service;

import java.util.List;

import com.azhuang.domain.Dict;
import com.azhuang.domain.PageBean;

public interface DictService {

	List<Dict> findDictByCode(String codeType) throws Exception;
	
}
