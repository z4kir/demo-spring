package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.example.demo.jpa.DemoRepository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String uni_id;
	private String name;

	@Transient
	@Autowired
	@Lazy
	private DemoRepository myEntityRepository;

//	@PrePersist
//	public void assignSrno() {
//		// Get the ApplicationContext
//		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//		DemoRepository demoRepository = context.getBean(DemoRepository.class);
//		if (demoRepository != null) { // Ensure the repository is available
//			Integer maxSrno = demoRepository.findMaxId();
//			this.id = (maxSrno == null ? 1 : maxSrno + 1);
//		}
//	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(String uni_id, String name) {
		super();
		this.uni_id = uni_id;
		this.name = name;
	}

	public String getUni_id() {
		return uni_id;
	}

	public void setUni_id(String uni_id) {
		this.uni_id = uni_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "test [uni_id=" + uni_id + ", id=" + id + ", name=" + name + "]";
	}

}
