package com.example.demo.config;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;

public class ContextAwareRunnable implements Runnable {

	final Runnable task;
	private final RequestAttributes context;
	private final GlobalValue globalValueCopy;
	private final GlobalValueThreadLocal globalValueThreadLocal;

	ContextAwareRunnable(Runnable task, RequestAttributes context, GlobalValueThreadLocal globalValueThreadLocal) {
		this.task = task;
		this.context = context;
		this.globalValueThreadLocal = globalValueThreadLocal;

		// Capture a cloned copy of GlobalValue (to prevent mutation issues)
		this.globalValueCopy = (globalValueThreadLocal.get() != null) ? globalValueThreadLocal.get().clone() : null;
	}

	@Override
	public void run() {
		if (context != null) {
			RequestContextHolder.setRequestAttributes(context, true);
		}

		// Set the cloned GlobalValue for this thread
		if (globalValueCopy != null) {
			globalValueThreadLocal.set(globalValueCopy);
		}

//        if you have any other Spring/Hystrix managed request object then you should 
//        wire those as well here. 

		try {
			task.run();
		} finally {
			RequestContextHolder.resetRequestAttributes();
//            close any other object you initialize in the run method. 
		}
	}
}