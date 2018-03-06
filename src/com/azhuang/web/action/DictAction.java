package com.azhuang.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.azhuang.domain.Dict;
import com.azhuang.service.DictService;
import com.azhuang.utils.FastJsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

public class DictAction extends ActionSupport implements ModelDriven<Dict> {

	private Dict dict = new Dict();

	private DictService dictService;

	@Override
	public Dict getModel() {
		// TODO Auto-generated method stub
		return dict;
	}

	public void setDictService(DictService dictService) {
		this.dictService = dictService;
	}

	public String findDictByCode() {

		String dict_type_code = dict.getDict_type_code();
		System.out.println("dict_type_code = " + dict_type_code);

		//
		try {
			List<Dict> list = dictService.findDictByCode(dict_type_code);

			FastJsonUtil.write_json(ServletActionContext.getResponse(), FastJsonUtil.toJSONString(list));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return NONE;
	}

}
