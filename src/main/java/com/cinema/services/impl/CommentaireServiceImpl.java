package com.cinema.services.impl;

import org.springframework.stereotype.Service;

import com.cinema.models.Commentaire;
import com.cinema.repositories.CommentaireRepository;
import com.cinema.services.CommentaireService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class CommentaireServiceImpl extends CRUDServiceImpl<Commentaire> implements CommentaireService{

	public CommentaireServiceImpl(CommentaireRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

}
