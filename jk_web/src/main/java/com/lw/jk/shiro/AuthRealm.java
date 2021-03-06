package com.lw.jk.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.lw.jk.pojo.Module;
import com.lw.jk.pojo.Role;
import com.lw.jk.pojo.User;
import com.lw.jk.service.UserService;
/**
 * 登录realm
 * @author lw
 */
public class AuthRealm extends AuthorizingRealm {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 授权
	// 当jsp页面出现shiro标签时调用该方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		System.out.println("4、授权");
		// 根据认证对象的第三个参数   从realm中获取当前用户
		User user = (User) collection.fromRealm(this.getName()).iterator().next();
		// 获取角色对象
		Set<Role> roles = user.getRole();
		List<String> permissions = new ArrayList<>();
		for (Role role : roles) {
			//	从角色中获取获取对应模块	
			Set<Module> modules = role.getModules();
			for (Module m : modules) {
				// 将该用户对应模块
				permissions.add(m.getName());
			}
		}
		// 创建授权对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 将模块集合对象添加到授权对象中
		info.addStringPermissions(permissions);
		return info;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("2、认证   token:" + token.hashCode());
		// 1、向下转型
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		// 2、调用service的方法查询数据库
		String hql = "from User where userName=?";
		List<User> list = userService.find(hql, User.class, new String[] { usernamePasswordToken.getUsername() });
		System.out.println("size:" + list.size());
		if (list != null && list.size() > 0) {
			User user = list.get(0);
			// 通过user对象和user对象的密码创建AuthenticationInfo对象
			// 认证信息对象
			AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
			return info; // 马上进入密码比较器
		}
		return null;
	}

}
