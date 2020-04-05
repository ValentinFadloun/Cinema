package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import org.springframework.stereotype.Service;

import com.cinema.models.Salle;
import com.cinema.repositories.SalleRepository;
import com.cinema.services.SalleService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Salles qui implément l'interface Salle Service
 *
 */
@Service
public class SalleServiceImpl extends CRUDServiceImpl<Salle> implements SalleService{

	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public SalleServiceImpl(SalleRepository repo) {
		super(repo);
	}
}
