package com.cinema.services;

import java.util.List;

import com.cinema.models.Seance;
import com.cinema.services.crud.CRUDService;

public interface SeanceService extends CRUDService<Seance>{
	
	public Seance addAssister(String idSeance, String idClient);
	public List<Seance> findByFilmNom(String titre);
}
