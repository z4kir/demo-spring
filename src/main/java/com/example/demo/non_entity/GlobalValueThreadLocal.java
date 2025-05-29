package com.example.demo.non_entity;

import org.springframework.stereotype.Component;

@Component
public class GlobalValueThreadLocal {

	private static final InheritableThreadLocal<GlobalValue> threadLocal = new InheritableThreadLocal<>();

	public GlobalValue get() {
		return threadLocal.get();
	}

	public void set(GlobalValue globalValue) {
		threadLocal.set(globalValue.clone());
	}

	public void remove() {
		threadLocal.remove();
	}

}
