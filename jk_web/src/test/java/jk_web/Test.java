package jk_web;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lw.jk.pojo.Dept;

public class Test {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sf.openSession();
		Query query = session.createQuery("from Dept");
		List list = query.list();
		System.out.println("list:" + list);
		session.close();
		sf.close(); 
	}
}
