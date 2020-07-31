package pj2002.hibernate.exercise;

import java.util.*;

import org.apache.log4j.Logger;

import pj2002.hibernate.exercise.dao.BillDao;
import pj2002.hibernate.exercise.dao.ProductDAO;
import pj2002.hibernate.exercise.dao.UserDAO;
import pj2002.hibernate.exercise.entity.Bill;
import pj2002.hibernate.exercise.entity.Product;
import pj2002.hibernate.exercise.entity.User;
import pj2002.hibernate.exercise.service.ShoppingService;

public class Test {

	public final static Logger logger = Logger.getLogger(Test.class);
	private static ShoppingService shoppingService;
	private static UserDAO userDAO ;
	private static ProductDAO productDAO;
	private static BillDao billDao;
	public static void main(String[] args) {
		userDAO=new UserDAO();
		productDAO= new ProductDAO();
		billDao=new BillDao();
		shoppingService = new ShoppingService(userDAO,productDAO,billDao);
		//crete user
		User user =shoppingService.createUser("Nguyễn văn Tân", "Hưng Yên, Văn Giang",36);

		shoppingService.insertProduct("Prod1", 100, 300);
		shoppingService.insertProduct("Prod2", 50, 30);
		shoppingService.insertProduct("Prod3", 90, 40);

		List<Product> products=	shoppingService.getProductList();

		Set<Product> buyedProduct=new HashSet<Product>();
		for (Product pr : products ) {

			buyedProduct.add(pr);
			if(buyedProduct.size()>=2)
			{
				break;
			}
		}

		Bill bill=shoppingService.userBuyProducts(buyedProduct);

		List<Bill> bills =new ArrayList<Bill>();
		bills.add(bill);
		user.setBills(bills);
		bill.setUser(user);
		userDAO.update(user);


	}


 public  static  List<Bill> createBills(User user){
	 BillDao billDao =new BillDao();
	 List<Bill> bills =new ArrayList<Bill>();
	 for (int i = 0; i< 10; i++) {
	 	Bill b =new Bill();
	 	b.setUser(user);
	 	Set<Product> prod =new HashSet<Product>();
		 for (int j = 0; j< 10; j++) {
			 Product product =new Product();
			 product.setName("Product"+(i*j));
			 product.setPrice((i*j*100));
			 product.setSize((i*j*2));
			 prod.add(product);
		 }
		b.setProducts(prod);
	 	b.setBuyDate(Calendar.getInstance().getTime());
	 	bills.add(b);
	 }

	return  bills;
 }

}

