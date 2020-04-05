package com.cinema.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.repositories.CommentaireRepository;
import com.cinema.services.CommentaireService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class CommentaireServiceImpl extends CRUDServiceImpl<Commentaire> implements CommentaireService{

	@Autowired
	CommentaireRepository repo;
	
	public CommentaireServiceImpl(CommentaireRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float filmMoyenne(Film f) {
		// TODO Auto-generated method stub
		List<Commentaire> listCommentaire = this.repo.findAllByFilm(f);
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
