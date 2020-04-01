package com.cinema.services.crud;

import java.util.Optional;

public interface CRUDService<T> {
	public T save(T a);
	public T update(T a);
	public void delete(String id);
	public Iterable<T> findAll();
	public Optional<T> findById(String id);
}