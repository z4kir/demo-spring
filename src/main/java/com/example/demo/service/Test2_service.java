package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;

@Service
public class Test2_service {

	@Autowired
	@Lazy
	GlobalValueThreadLocal globalValueThreadLocal;

	public void mutableTest2() {
		GlobalValue gv = globalValueThreadLocal.get();
		System.out.println("newest " + gv.hashCode() + " " + gv.getName());
	}

}
