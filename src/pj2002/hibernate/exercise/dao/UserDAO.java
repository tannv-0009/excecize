package pj2002.hibernate.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import pj2002.hibernate.exercise.entity.User;
import pj2002.hibernate.exercise.util.HibernateUtil;

public class UserDAO {
	
	public User insertUser(User user) {
	Session session =HibernateUtil.getSessionFactory().openSession();
	Transaction transaction=session.beginTransaction();

		try {
			session.save(user);
			transaction.commit();
		}
		catch (HibernateException exeption)
		{
			transaction.rollback();
			System.out.println("Error:"+   exeption.getMessage());
		}
		finally {
			session.close();
		}
		
		return user;
	}

	public User update(User user) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();
			session.update(user);
			transaction.commit();
		}
		catch (HibernateException exeption)
		{
			session.close();
			System.out.println("Error:"+   exeption.getMessage());
		}
		finally {
			session.close();
		}

		return user;
	}


	public boolean delete(Integer userId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();

			session.delete(findById(userId));

			transaction.commit();
		}
		catch (HibernateException exeption)
		{
			session.close();
			System.out.println("Error:"+   exeption.getMessage());
			return  false;
		}
		finally {
			session.close();
		}

		return true;
	}


	public User findById(Integer userId) {
		User user=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<User> query= session.createQuery("From USER WHERE id = :userId");
			query.setParameter("userId",userId);
			user = query.getSingleResult();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return user;
	}

	public List<User> findByAll() {
		List<User> users=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<User> query= session.createQuery("From User");
			users = query.getResultList();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return users;
	}




}
