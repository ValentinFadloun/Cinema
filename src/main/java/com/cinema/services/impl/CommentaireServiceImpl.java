package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.repositories.CommentaireRepository;
import com.cinema.services.CommentaireService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Commentaires qui implément l'interface Commentaire Service
 *
 */
@Service
public class CommentaireServiceImpl extends CRUDServiceImpl<Commentaire> implements CommentaireService{

	/*
	 * Déclaration des variables permettant d'accéeder au Repository de Commentaire
	 */
	@Autowired
	CommentaireRepository repo;
	
	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public CommentaireServiceImpl(CommentaireRepository repo) {
		super(repo);
	}

	/**
	 * Methode permettant de calculer la moyenne d'un film 
	 * @param film
	 * @return float
	 */
	@Override
	public float filmMoyenne(Film film) {
		List<Commentaire> listCommentaire = this.repo.findAllByFilm(film);
		float somme = 0F;
		int compteur = 0;
		float resultat = 0F;
		for (Commentaire commentaire : listCommentaire) {
			somme += commentaire.getNote();
			compteur += 1;
		}
		if(compteur > 0) {
			resultat = somme/compteur;
		}
		return resultat;
	}
}
