package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;
import com.example.demo.service.Async_service;

@RestController
public class Async_controller {

	@Autowired
	Async_service async_service;

	@Autowired
	@Lazy
	GlobalValue globalValue;

	@Autowired
	@Lazy
	GlobalValueThreadLocal globalValueThreadLocal;

	@GetMapping("test/async")
	private String testAsync() throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();

		String[] arr = { "amir", "salman", "irfan", "shahrukh", "junaid", "feroz", "saif" };
		List<CompletableFuture<String>> futures = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			futures.add(async_service.asyncTest(arr[i]));
		}
		System.out.println(futures.get(0).get());
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		long endTime = System.currentTimeMillis();
		long diffrence = endTime - startTime;
		System.out.println("Time Difference " + diffrence);
		return futures.stream().map(e -> {
			try {
				return e.get();
			} catch (InterruptedException | ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return "Error :" + e1.getMessage();
			}
		}).collect(Collectors.joining(","));
	}

	@GetMapping("test/sync")
	private String testSync() throws InterruptedException, ExecutionException {
		long startTime = System.currentTimeMillis();

		String[] arr = { "amir", "salman", "irfan", "shahrukh", "junaid", "feroz", "saif" };
		List<String> futures = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			futures.add(async_service.syncTest(arr[i]));
		}
//		System.out.println(futures.get(0));
		long endTime = System.currentTimeMillis();
		long diffrence = endTime - startTime;
		System.out.println("Time Difference " + diffrence);
		return futures.stream().collect(Collectors.joining(","));
	}

	@GetMapping("test/mutable")
	private void testMutability() {
		globalValue.setName("farhan ");
		System.out.println("old " + globalValue.hashCode());
		globalValue.setUser_id("farsay ");
		globalValueThreadLocal.set(globalValue);

		for (int i = 0; i < 4; i++) {
			async_service.mutableTest(i);
		}
	}

}
