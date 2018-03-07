package com.azhuang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Registration;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.RegisteredSynchronization;
import org.junit.Test;

import com.azhuang.domain.Customer;
import com.azhuang.domain.Dict;
import com.azhuang.domain.PageBean;
import com.azhuang.service.CustomerService;
import com.azhuang.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();

	private CustomerService customService;

	private Integer currPage = 1;
	private Integer pageSize = 2;

	// a:无参构造方法 在代码区域右键--source--Generate Constructors from Superclass //
	// alt+shift+s +c
	// b:带参构造方法 在代码区域右键--source--Generate Constructors using fields.. -- finish
	// //alt+shift+s +o
	// 生成get/set方法
	// 在代码区域右键--source--Generate Getters and Setters... //alt+shift+s +r

	// 要上传的文件
	private File upload;
	// 文件的名称
	private String uploadFileName;
	// 文件的MIME的类型
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setCurrPage(Integer currPage) {

		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 2;
		}
		this.pageSize = pageSize;
	}

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomService(CustomerService customService) {
		this.customService = customService;
	}

	public String findCustomerPage() {

		System.out.println("findCustomerPage = ");

		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		// criteria.add(Restrictions.eq(propertyName, value))

		String cust_name = customer.getCust_name();
		Dict level = customer.getLevel();
		Dict source = customer.getSource();

		// 筛选的条件
		if (cust_name != null && !cust_name.trim().equals("")) {
			criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
			System.out.println("cust_name = " + cust_name);
		}
		if (level != null && !level.getDict_id().trim().isEmpty()) {
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
			System.out.println("level.dict_id = " + level.getDict_id());
		}

		if (source != null && !source.getDict_id().isEmpty()) {
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
			System.out.println("source.dict_id= " + source.getDict_id());
		}

		try {
			PageBean<Customer> pageBean = customService.findCustomerPage(currPage, pageSize, criteria);

			// 压栈
			ValueStack valueStack = ActionContext.getContext().getValueStack();
			valueStack.set("pageBean", pageBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "page";
	}

	public String addCustomerUi() {

		return "addCustomerUi";

	}

	// customer_addCustomer
	public String addCustomer() {

		System.out.println("addCustomer addCustomer= ");
		// 做文件的上传，说明用户选择了上传的文件了
		if (uploadFileName != null) {
			// 打印
			System.out.println("文件类型：" + uploadContentType);
			// 把文件的名称处理一下
			String uuidname = UploadUtils.getUUIDName(uploadFileName);
			// 把文件上传到D:\\apache-tomcat-7.0.52\\webapps\\upload
			String path = "G:\\apache-tomcat-7.0.82\\webapps\\upload\\";
			// String path = "D:\\apache-tomcat-7.0.52\\webapps\\upload\\";
			// 创建file对象
			File file = new File(path + uuidname);
			// 简单方式
			try {
				FileUtils.copyFile(upload, file);
				// 把上传的文件的路径，保存到客户表中
				customer.setFilepath(path + uuidname);
				// 保存客户成功了
				customService.save(customer);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "addCustomer";
	}

	public String editUi() {

		// 默认customer压栈的了，Action默认压栈，model是Action类的书写 getModel(返回customer对象)
		try {

			customer = customService.findById(customer.getCust_id());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 方法二:在Action中使用ActionContext得到parameterMap获取参数:
		ActionContext context = ActionContext.getContext();
		Map parameterMap = context.getParameters();
		Map session = context.getSession();// 得到会话

		session.put("customer", customer);

		String cust_id[] = (String[]) parameterMap.get("cust_id");

		System.out.println("cust_id" + cust_id[0] + "  customer = " + customer);

		return "editUi";

	}

	public String updateCustomer() {

		if (uploadFileName != null) {
			// // 先删除旧的图片
			String oldFilepath = customer.getFilepath();
			if (oldFilepath != null && !oldFilepath.trim().isEmpty()) {
				// 说明，旧的路径存在的，删除图片
				File f = new File(oldFilepath);
				f.delete();
			}

			// 打印
			System.out.println("文件类型：" + uploadContentType);
			// 把文件的名称处理一下
			String uuidname = UploadUtils.getUUIDName(uploadFileName);
			// 把文件上传到D:\\apache-tomcat-7.0.52\\webapps\\upload
			String path = "G:\\apache-tomcat-7.0.82\\webapps\\upload\\";
			// String path = "D:\\apache-tomcat-7.0.52\\webapps\\upload\\";
			// 创建file对象
			File file = new File(path + uuidname);
			// 简单方式
			try {
				FileUtils.copyFile(upload, file);
				// 把上传的文件的路径，保存到客户表中
				customer.setFilepath(path + uuidname);
				// 保存客户成功了
				customService.updateCustomer(customer);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "updateCustomer";
	}

	public String  deleteCoustomer(){
		
		try {
			customer = customService.findById((customer.getCust_id()));
			System.out.println("deleteCoustomer id：" + customer.getCust_id());
			customService.deleteCoustomer(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "deleteCoustomer";
		
	}

}
