package pj2002.hibernate.exercise.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pj2002.hibernate.exercise.entity.Bill;
import pj2002.hibernate.exercise.util.HibernateUtil;

import java.util.List;

public class BillDao {
	
	public Bill insert(Bill bill) {
	Session session =HibernateUtil.getSessionFactory().openSession();
	Transaction transaction=session.beginTransaction();

		try {
			session.save(bill);
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
		
		return bill;
	}

	public Bill update(Bill bill) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();
			session.update(bill);
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

		return bill;
	}


	public boolean delete(Integer billId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();

			session.delete(findById(billId));

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


	public Bill findById(Integer billId) {
		Bill bill=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Bill> query= session.createQuery("From Bill WHERE id = :billId");
			query.setParameter("billId",billId);
			bill = query.getSingleResult();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return bill;
	}

	public List<Bill> findByAll() {
		List<Bill> bills=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Bill> query= session.createQuery("From Bill");
			bills = query.getResultList();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return bills;
	}




}
