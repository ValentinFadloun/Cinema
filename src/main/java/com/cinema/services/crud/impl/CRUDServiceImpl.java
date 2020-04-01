package com.cinema.services.crud.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Client;
import com.cinema.repositories.AssisterRepository;
import com.cinema.services.crud.CRUDService;

public abstract class CRUDServiceImpl<T> implements CRUDService<T>{
	
	private MongoRepository<T, String> repo;
	
	public CRUDServiceImpl(MongoRepository<T, String> repo) {
		// TODO Auto-generated constructor stub
		this.repo = repo;
	}
	
	public T save(T c) {
		// TODO Auto-generated method stub
		return this.repo.save(c);
	}

	@Override
	public T update(T c) {
		// TODO Auto-generated method stub
		return this.repo.save(c);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
	}

	@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Optional<T> findById(String id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id);
	}
}
