package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class Async_service {

	@Autowired
	@Lazy
	Test_service test_service;

	@Async("asyncTaskExecuter")
	public CompletableFuture<String> asyncTest(String val) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newVal = val.concat(" " + Thread.currentThread().getName());
		return CompletableFuture.completedFuture(newVal);
	}

	public String syncTest(String val) {
		String newVal = val.concat(" " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newVal;
	}

	@Async("asyncTaskExecuter")
	public void mutableTest(int count) {
		test_service.mutableTest(count);
	}
}
