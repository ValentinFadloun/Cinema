package com.cinema.services.crud;

/**
 * @author Valentin Fadloun
 */

import java.util.Optional;

/**
 * 
 * Création de l'interface Service général
 * Elle permet de définir les méthodes général a tout les services
 *
 */
public interface CRUDService<T> {
	public T save(T objet);
	public T update(T objet);
	public void delete(T objet);
	public void delete(String id);
	public Iterable<T> findAll();
	public Optional<T> findById(String id);
}