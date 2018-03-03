package com.azhuang.web.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.azhuang.domain.User;
import com.azhuang.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	private User user = new User();

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public String checkCode() {

		// 获取用户名看看并查看是否存在
		try {
			User mUser = userService.checkCode(user.getUser_code());
			// 获取response对象
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			if (mUser == null) {
				// yes代表可以注册
				response.getWriter().print("yes");

			} else {
				// no代表不可以注册
				response.getWriter().print("no");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return NONE;
	}

	public String regist() {

		// 获取用户名看看并查看是否存在
		try {
			userService.regist(user);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return LOGIN;
	}

	public String login() {

		// 获取用户名看看并查看是否存在
		try {
			User mUser = userService.login(user);

			System.out.println("mUser = " + mUser+"  user = "+user);

			if (mUser == null) {
				return LOGIN;

			} else {
				ServletActionContext.getRequest().getSession().setAttribute("user", mUser);
				return "loginOK";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return LOGIN;
	}
	public String exit() {
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		
		return LOGIN;
	}
}
