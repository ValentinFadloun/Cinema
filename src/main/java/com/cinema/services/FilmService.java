package com.cinema.services;

import java.util.List;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

public interface FilmService extends CRUDService<Film>{
	
	public List<Film> findAllByTitre(String titre);
	public List<Film> findAllByGenre(String genre);
	public List<Film> findAllByAge(int age);
	public float recetteFilm(String id);
	public int findAgeLimite(Film f);
	public Commentaire addCommentaire(Commentaire commentaire);
}
