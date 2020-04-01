package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Seance;

public interface SeanceService {
	public Seance save(Seance s);
	public Seance update(Seance s);
	public void delete(String id);
	public List<Seance> findAll();
	public Optional<Seance> findById(String id);
	public Seance addAssister(String idSeance, String idClient);

}
