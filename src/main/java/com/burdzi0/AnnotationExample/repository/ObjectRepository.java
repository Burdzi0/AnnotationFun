package com.burdzi0.AnnotationExample.repository;

import com.burdzi0.AnnotationExample.model.Cat;
import com.burdzi0.AnnotationExample.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ObjectRepository {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("all");
	private EntityManager manager = factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();

	public void saveObject(Object object) {
		transaction.begin();
		manager.persist(object);
		transaction.commit();
	}

	public void close() {
		factory.close();
		manager.close();
	}
}
