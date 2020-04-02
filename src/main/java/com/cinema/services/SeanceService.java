package com.cinema.services;

import java.time.LocalDateTime;
import java.util.List;

import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.services.crud.CRUDService;

public interface SeanceService extends CRUDService<Seance>{
	
	public Seance addClientToSeance(String idSeance, String idClient);
	public List<Seance> findAllByFilmNom(String titre);
	public List<Seance> findAllByFilm(Film f);
	public float recetteSeance(String id);
	public int placesSeance(String id);
	public List<Seance> findAllByCrenauxSeance(LocalDateTime min, LocalDateTime max);
}
