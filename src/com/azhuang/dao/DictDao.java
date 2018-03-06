package com.azhuang.dao;

import java.util.List;

import com.azhuang.domain.Dict;

public interface DictDao {

	List<Dict> findDictByCode(String codeType) throws Exception;

}
