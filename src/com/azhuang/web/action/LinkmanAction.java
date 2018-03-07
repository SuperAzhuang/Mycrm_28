package com.azhuang.web.action;

import com.azhuang.domain.Customer;
import com.azhuang.domain.Linkman;
import com.azhuang.service.LinkmanService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {

	private Linkman linkman = new Linkman();

	private LinkmanService linkmanService;

	@Override
	public Linkman getModel() {
		// TODO Auto-generated method stub

		return linkman;
	}

	// ${pageContext.request.contextPath}/
	// linkman_findByPage.action
	public String findByPage() {

		linkmanService.
		
		return "linkman_page";
	}

	public String addUi() {

		return "addUi";
	}
}
