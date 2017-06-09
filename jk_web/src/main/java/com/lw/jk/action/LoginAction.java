package com.lw.jk.action;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.lw.jk.pojo.LoginLog;
import com.lw.jk.pojo.User;
import com.lw.jk.service.LoginLogService;
import com.lw.jk.utils.SysConstant;
import com.lw.jk.utils.UtilFuns;

/**
 * 登录和退出Action
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private LoginLogService loginLogService;
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	// SSH传统登录方式
	public String login() throws Exception {
		if (UtilFuns.isEmpty(username)) {
			return "login"; // 直接跳转到登录页面
		}
		try {
			// 1、获取Subject
			Subject subject = SecurityUtils.getSubject();
			// 2、调用登录方法
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token); // 会到realm中的doGetAuthenticationInfo方法
			// 3、登录成功时从shiro中取出登录信息
			User user = (User) subject.getPrincipal();
			// 4、将用户信息存入session
			session.put(SysConstant.CURRENT_USER_INFO, user);
			// 创建登录日志
			LoginLog ll = new LoginLog() ;
			ll.setId(user.getId());
			ll.setIpAddress(super.getIpAddress());
			ll.setLoginName(user.getUserName());
			ll.setLoginTime(new Date());
			loginLogService.saveOrUpdate(ll);
		} catch (Exception e) {
			e.printStackTrace();
			session.put("errorInfo", "用户名密码错误");
			return "login";
		}
		return "success";
	}

	// 退出
	public String logout() {
		session.remove(SysConstant.CURRENT_USER_INFO); // 删除session

		return "logout";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
