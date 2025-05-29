package com.example.demo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Test;

public interface DemoRepository extends JpaRepository<Test, String> {
	
	 @Query("SELECT COALESCE(MAX(e.id), 0) FROM Test e")
	    Integer findMaxId();
	 
}
