package pj2002.hibernate.exercise;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pj2002.hibernate.exercise.entity.Cart;
import pj2002.hibernate.exercise.entity.Product;
import pj2002.hibernate.exercise.entity.User;
import pj2002.hibernate.exercise.util.HibernateUtil;

public class Test {

	public final static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
//			Transaction t = session.beginTransaction();

			List<User> users = session.createQuery("FROM User").getResultList();
			for (User user : users) {
				logger.info(user.getId() + " -- " + user.getName());
				for (Cart cart : user.getCarts()) {
					Product product = session.find(Product.class, cart.getProductId());
					logger.info("product: " + product.getName());
				}
			}

//			Cart cart = session.find(Cart.class, 1);
//			logger.info(cart.getProductId());

//			t.commit();
			logger.info("successfully saved");
			session.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
