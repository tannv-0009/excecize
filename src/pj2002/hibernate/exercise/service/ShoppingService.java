package pj2002.hibernate.exercise.service;

import pj2002.hibernate.exercise.dao.BillDao;
import pj2002.hibernate.exercise.dao.ProductDAO;
import pj2002.hibernate.exercise.dao.UserDAO;
import pj2002.hibernate.exercise.entity.Bill;
import pj2002.hibernate.exercise.entity.Product;
import pj2002.hibernate.exercise.entity.User;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ShoppingService {

	/**
	 * 1. Create user
	 * 2. Create products
	 * 3. Get List products
	 * 4. User choseProducts and buy it
	 * 		- This will create Bill from list user chose product
	 * 	    - This also insert into bill_product with list product has buy by user
	 */
	private UserDAO userDAO;
	private ProductDAO productDAO;
	private BillDao billDao;


	public  ShoppingService(UserDAO userDAO, ProductDAO productDAO, BillDao billDao)
	{
		this.userDAO=userDAO;
		this.productDAO=productDAO;
		this.billDao=billDao;
	}


	public User createUser(String userName, String address, int age)
	{
		User  user =new User();
		user.setName(userName);
		user.setAddress(address);
		user.setAge(age);
		userDAO.insert(user);
	return  user;
	}

	public Product insertProduct(String prodName,Integer size, Integer price )
	{
		Product  product =new Product();
		product.setName(prodName);
		product.setSize(size);
		product.setPrice(price);
		productDAO.insert(product);
		return  product;
	}

	public List<Product> getProductList(){
		List<Product> products =productDAO.findByAll();
		return  products;
	}

	public Bill userBuyProducts(Set<Product> products){
		Bill bill =new Bill();
		bill.setProducts(products);
		bill.setBuyDate(Calendar.getInstance().getTime());
		billDao.insert(bill);
		return bill;
	}



}
