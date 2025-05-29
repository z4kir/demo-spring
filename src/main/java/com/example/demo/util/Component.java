package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.example.demo.jpa.Test_jpa;

public class Component {

	@Autowired
	@Lazy
	Test_jpa test_jpa;

	public void test() {
//		test_jpa.toString();
	}

}
