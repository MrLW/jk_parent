package com.lw.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.lw.jk.dao.BaseDao;
import com.lw.jk.pojo.User;
import com.lw.jk.service.UserService;
import com.lw.jk.utils.Encrypt;
import com.lw.jk.utils.Page;
import com.lw.jk.utils.SysConstant;
import com.lw.jk.utils.UtilFuns;


public class UserServiceImpl implements UserService {

	private BaseDao baseDao;
	private SimpleMailMessage mailMessage;
	private JavaMailSenderImpl mailSender;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setMailMessage(SimpleMailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public List<User> find(String hql, Class<User> entityClass, Object[] params) {

		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(final User entity) {
		// 判断是否是新加的部门
		if (UtilFuns.isEmpty(entity.getId())) {
			
			entity.setState(1);
			// 生成UUID并且设置
			String uuid = UUID.randomUUID().toString();
			entity.setId(uuid);
			entity.getUserInfo().setId(uuid);
			// 设置密码
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));
			
			sendMessageToEmail(entity);
		}
		baseDao.saveOrUpdate(entity);
	}

	/**
	 * 发送消息给到邮箱
	 * @param entity
	 */
	private void sendMessageToEmail(final User entity) {
		// 由于发送邮件是耗时操作,因此开启线程
		new Thread() {
			public void run() {
				try {
					mailMessage.setTo(entity.getUserInfo().getEmail());
					mailMessage.setSubject("新员工入职的系统账户通知");
					mailMessage.setText("欢迎您加入本集团，您的用户名:" + entity.getUserName() + ",初始密码：" + SysConstant.DEFAULT_PASS);

					mailSender.send(mailMessage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	@Override
	public void saveOrUpdateAll(Collection<User> entitys) {

	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteById(Class<User> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<User> entityClass, Serializable[] ids) {
		// 遍历调用上面删除一个id的方法
		for (Serializable id : ids) {
			this.deleteById(User.class, id);
		}
	}

}
