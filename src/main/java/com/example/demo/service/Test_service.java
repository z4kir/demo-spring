package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Test;
import com.example.demo.jpa.Test_jpa;
import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;

@Service
public class Test_service {

	@Autowired
	@Lazy
	GlobalValueThreadLocal globalValueThreadLocal;

	@Autowired
	@Lazy
	Test2_service test2_service;

	@Autowired
	@Lazy
	Test_jpa test_jpa;

	public void mutableTest(int count) {
		GlobalValue gv = globalValueThreadLocal.get();
		if (count == 1) {
			gv.setName("zakir");
		}

		System.out.println("new " + gv.hashCode() + " " + gv.getName());
		test2_service.mutableTest2();
	}

	public void insertInBatch() {
		long startTime = System.currentTimeMillis();
		List<Test> test_list = new ArrayList<Test>();
		for (int i = 0; i < 500; i++) {
//			System.out.println(i);
			Test test = new Test();
			test.setName("za " + i);
			test.setUni_id("sasadads-sss" + i);
//			test_list.add(test);
//			if (test_list.size() % 100 == 0) {
//				System.out.println(test_list.size());
//				test_jpa.batchInsertWithRetry(test_list, 100);
//				test_list.clear();
//			}
			test_jpa.insert(test);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time difference---> " + (endTime - startTime));
	}
}
