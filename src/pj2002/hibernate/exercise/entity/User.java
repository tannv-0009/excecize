package pj2002.hibernate.exercise.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	private Integer id;
	private String name;
	private Integer age;
	private String address;

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	private List<Bill> bills;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return " Id: "+this.getId() + " - Age: "+this.getAge() + " - " + this.getName()+ " Address: "+ this.getAddress();
	}
}
