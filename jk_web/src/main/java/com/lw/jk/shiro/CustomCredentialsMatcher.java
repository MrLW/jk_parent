package com.lw.jk.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.lw.jk.utils.Encrypt;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	// 密码比较的方法
	// token：用户输入的用户名密码封装; info:从数据库中查询的信息
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		System.out.println("3、密码比较");
		UsernamePasswordToken viewToken = (UsernamePasswordToken) token;
		String pwd = Encrypt.md5(new String(viewToken.getPassword()), viewToken.getUsername());
		// 取出数据库中加密的密码
		Object dbpwd = info.getCredentials();
		return this.equals(pwd, dbpwd);
	}

}
