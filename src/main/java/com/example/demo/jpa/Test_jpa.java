package com.example.demo.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Test;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class Test_jpa {

	List<Test> failedRecords = new ArrayList<>();

	@Autowired
	@Lazy
	EntityManager entityManager;

	public void insert(Test test) {
		entityManager.persist(test);
	}

	public Test findById(Test test) {
		return entityManager.find(Test.class, test.getId());
	}

	public Test update(Test test) {
		return entityManager.merge(test);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println("object testing");
		return super.toString();
	}

	@Transactional
	public void batchInsertWithRetry(List<Test> entities, int batchSize) {
		List<Test> failedRecords = new ArrayList<>();

		for (int i = 0; i < entities.size(); i++) {
			try {
				entityManager.persist(entities.get(i));

				if (i > 0 && i % batchSize == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			} catch (Exception e) {
				failedRecords.add(entities.get(i));
				System.err.println("Failed record saved for retry: " + entities.get(i));
			}

			// Ensure the last batch is flushed
			entityManager.flush();
			entityManager.clear();

			// Retry failed records if any
			if (!failedRecords.isEmpty()) {
				System.out.println("Retrying failed records...");
				batchInsertWithRetry(failedRecords, batchSize);
			}
		}
	}

}
