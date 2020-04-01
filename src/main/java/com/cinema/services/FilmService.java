package com.cinema.services;

import java.util.List;

import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

public interface FilmService extends CRUDService<Film>{
	
	public List<Film> findAllByTitre(String titre);
}
