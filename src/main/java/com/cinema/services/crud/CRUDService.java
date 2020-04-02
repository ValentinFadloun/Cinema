package com.cinema.services.crud;

import java.util.Optional;

public interface CRUDService<T> {
	public T save(T objet);
	public T update(T objet);
	public void delete(T objet);
	public void delete(String id);
	public Iterable<T> findAll();
	public Optional<T> findById(String id);
}