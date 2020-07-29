package pj2002.hibernate.exercise;

import java.util.List;

import org.apache.log4j.Logger;

import pj2002.hibernate.exercise.dao.UserDAO;
import pj2002.hibernate.exercise.entity.User;

public class Test {

	public final static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		
		User user =new User();
		user.setAddress("Address Today");
		user.setName("Nguyen Van Tan");
		user.setAge(36);
		
		UserDAO userDao =new UserDAO();

		// thêm user
		User userInserted=userDao.insertUser(user);
		logger.info(userInserted);

		// tìm user Id =3 trong database
		User userFound= userDao.findById(3);
		logger.info(userFound);

		// thay đổi giá trị của user tìm thấy trong db, gọi update
		userFound.setAddress("Updated today");
		userDao.update(userFound);

		//in user đã dc update ra
		logger.info(userFound);


	}

}
