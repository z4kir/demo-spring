package com.example.demo.config;

import org.springframework.core.task.TaskDecorator;

import com.example.demo.non_entity.GlobalValue;
import com.example.demo.non_entity.GlobalValueThreadLocal;

public class ThreadLocalTaskDecorator implements TaskDecorator {

	private final GlobalValueThreadLocal globalValueThreadLocal;

	public ThreadLocalTaskDecorator(GlobalValueThreadLocal globalValueThreadLocal) {
		this.globalValueThreadLocal = globalValueThreadLocal;
	}

	@Override
	public Runnable decorate(Runnable task) {
		return () -> {
			// Clone and set the ThreadLocal value for this task
			GlobalValue clonedValue = globalValueThreadLocal.get() != null ? globalValueThreadLocal.get().clone()
					: null;
			if (clonedValue != null) {
				globalValueThreadLocal.set(clonedValue);
			}

			try {
				task.run(); // Execute the task
			} finally {
				// Clean up the ThreadLocal after task execution
				globalValueThreadLocal.remove();
			}
		};
	}
}
