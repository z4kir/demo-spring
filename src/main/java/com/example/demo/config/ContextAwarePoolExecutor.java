package com.example.demo.config;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.non_entity.GlobalValueThreadLocal;

public class ContextAwarePoolExecutor extends ThreadPoolTaskExecutor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final GlobalValueThreadLocal globalValueThreadLocal;

	// Constructor to inject GlobalValueThreadLocal
	public ContextAwarePoolExecutor(GlobalValueThreadLocal globalValueThreadLocal) {
		this.globalValueThreadLocal = globalValueThreadLocal;
	}

	@Override
	public void execute(Runnable task) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();

		super.execute(new ContextAwareRunnable(task, requestAttributes, this.globalValueThreadLocal));
	}

	@Override
	public <T> Future<T> submit(Callable<T> task) {
		return super.submit(new ContextAwareCallable<T>(task, RequestContextHolder.currentRequestAttributes()));
	}

	@Override
	public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
		return super.submitListenable(
				new ContextAwareCallable<T>(task, RequestContextHolder.currentRequestAttributes()));
	}
}