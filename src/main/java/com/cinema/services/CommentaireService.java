package com.cinema.services;

import java.util.List;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

public interface CommentaireService extends CRUDService<Commentaire>{

	public float filmMoyenne(Film f);
}
