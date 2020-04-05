package com.cinema.services.crud.impl;

/**
 * @author Valentin Fadloun
 */

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de la classe abstraite CRUDServiceImpl qui défini les méthodes général a tout les services
 *
 */
public abstract class CRUDServiceImpl<T> implements CRUDService<T>{
	
	private MongoRepository<T, String> repo;
	
	/**
	 * Constructeur permettant de définir le repository utilisé
	 * @param repo
	 */
	public CRUDServiceImpl(MongoRepository<T, String> repo) {
		this.repo = repo;
	}
	
	/**
	 * Methode permettant sauvegarder dans la Base de donnée l'objet envoyé
	 * @param objet
	 * @return T
	 */
	public T save(T objet) {
		return this.repo.save(objet);
	}

	/**
	 * Methode permettant mettre a jour dans la Base de donnée l'objet envoyé
	 * @param objet
	 * @return T
	 */
	@Override
	public T update(T objet) {
		return this.repo.save(objet);
	}
	
	/**
	 * Methode permettant supprimer dans la Base de donnée l'objet envoyé
	 * @param objet
	 */
	@Override
	public void delete(T objet) {
		this.repo.delete(objet);
	}

	/**
	 * Methode permettant supprimer an fonction de son id dans la Base de donnée l'objet envoyé
	 * @param objet
	 */
	@Override
	public void delete(String id) {
		this.repo.deleteById(id);
	}

	/**
	 * Methode permettant trouver tout les objets
	 * @return Iterable<T>
	 */
	@Override
	public Iterable<T> findAll() {
		return this.repo.findAll();
	}

	/**
	 * Methode permettant trouver un objet en fonction de son id
	 * @param id
	 * @return Optionnal<T>
	 */
	@Override
	public Optional<T> findById(String id) {
		return this.repo.findById(id);
	}
}
