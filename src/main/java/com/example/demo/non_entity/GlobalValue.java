package com.example.demo.non_entity;

import org.springframework.stereotype.Component;

@Component
public class GlobalValue implements Cloneable {

	String user_id;
	String name;
	String threadParam;

	public GlobalValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GlobalValue(String user_id, String name) {
		super();
		this.user_id = user_id;
		this.name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThreadParam() {
		return threadParam;
	}

	public void setThreadParam(String threadParam) {
		this.threadParam = threadParam;
	}

	@Override
	public GlobalValue clone() {
		try {
			return (GlobalValue) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Clone failed", e);
		}
	}

	@Override
	public String toString() {
		return "GlobalValue [user_id=" + user_id + ", name=" + name + ", threadParam=" + threadParam + "]";
	}

}
