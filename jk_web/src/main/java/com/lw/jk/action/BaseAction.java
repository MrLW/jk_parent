package com.lw.jk.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.lw.jk.pojo.User;
import com.lw.jk.utils.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// 通过RequestAware, SessionAware,
// ApplicationAware实行接口获得request,session,application对象，action中就可直接调用
/**
 * @author lw
 */
public class BaseAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {
	private static Logger log = Logger.getLogger(BaseAction.class);

	private static final long serialVersionUID = 1L;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	/**
	 * 将对象存入值栈顶
	 */
	public void push(Object obj) {
		ActionContext.getContext().getValueStack().push(obj);
	}
	
	/**
	 *  将栈顶元素移除
	 */
	public void pop() {
		ActionContext.getContext().getValueStack().pop();
	}

	/**
	 * 将key-value对放入值栈的 contextMap中
	 */
	public void put(String key ,Object value){
		ActionContext.getContext().put(key, value);
	}
	
	public User getCurrentUser(){
		return (User)session.get(SysConstant.CURRENT_USER_INFO) ;
	}

}
