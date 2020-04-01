package com.cinema.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Film;
import com.cinema.models.Seance;

public interface SeanceRepository extends MongoRepository<Seance, String> {
	
	public List<Seance> findAllByFilm(Film f);
}
