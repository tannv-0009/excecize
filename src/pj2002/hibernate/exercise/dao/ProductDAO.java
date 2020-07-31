package pj2002.hibernate.exercise.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pj2002.hibernate.exercise.entity.Product;
import pj2002.hibernate.exercise.util.HibernateUtil;

import java.util.List;

public class ProductDAO {
	
	public Product insert(Product product) {
	Session session =HibernateUtil.getSessionFactory().openSession();
	Transaction transaction=session.beginTransaction();

		try {
			session.save(product);
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
		
		return product;
	}

	public Product update(Product product) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();
			session.update(product);
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

		return product;
	}


	public boolean delete(Integer productId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Transaction transaction =session.beginTransaction();

			session.delete(findById(productId));

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


	public Product findById(Integer productId) {
		Product product=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Product> query= session.createQuery("From Product WHERE id = :productId");
			query.setParameter("productId",productId);
			product = query.getSingleResult();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return product;
	}

	public List<Product> findByAll() {
		List<Product> products=null;
		Session session =HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Product> query= session.createQuery("From Product");
			products = query.getResultList();
		}
		catch (Exception ex)
		{
			System.out.println("Error:"+   ex.getMessage());
		}
		finally {
			session.close();
		}

		return products;
	}




}
