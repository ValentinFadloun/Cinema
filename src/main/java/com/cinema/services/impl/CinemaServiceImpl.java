package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.models.Salle;
import com.cinema.repositories.CinemaRepository;
import com.cinema.services.CinemaService;
import com.cinema.services.SalleService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Cinemas qui implément l'interface Cinema Service
 *
 */
@Service
public class CinemaServiceImpl extends CRUDServiceImpl<Cinema> implements CinemaService {
	
	/*
	 * Déclaration des variables permettant d'accéeder au Repository de Cinema et au Service nécessaire
	 */
	@Autowired
	private CinemaRepository repo;
	@Autowired
	private SalleService salleService;

	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public CinemaServiceImpl(CinemaRepository repo) {
		super(repo);
	}

	/**
	 * Methode permettant la création d'un cinema ainsi que de ses salles.
	 * Elle va récuperer le cinema dans un cinemaDTO puis ajouter toutes les salles en définissant a quel cinema elles appartiennes (celui envoyé)
	 * @param cinema
	 */
	@Override
	public CinemaDTO saveCinema(CinemaDTO cinema) {
		this.repo.save(cinema.getCinema());
		List<Salle> salles = cinema.getSalles();
		List<Salle> newSalles = new ArrayList<Salle>();
		for (Salle salle : salles) {
			salle.setCinema(cinema.getCinema());
			newSalles.add(salle);
			this.salleService.save(salle);
		}
		cinema.setSalles(newSalles);
		return cinema;
	}
}
