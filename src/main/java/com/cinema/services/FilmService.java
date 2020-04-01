package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

public interface FilmService extends CRUDService<Film>{
	
	/*public Film save(Film f);
	public Film update(Film f);
	public void delete(String id);
	public List<Film> findAll();
	public Optional<Film> findById(String id);*/
	public List<Film> findAllByTitre(String titre);
}
