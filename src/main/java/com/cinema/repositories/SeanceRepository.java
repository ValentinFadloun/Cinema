package com.cinema.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.impl.SeanceRepositoryCustom;

public interface SeanceRepository extends MongoRepository<Seance, String>, SeanceRepositoryCustom {
	
	public List<Seance> findAllByFilm(Film film);
	public List<Seance> findAllByType(String type);
	public List<Seance> findAllByDateBetween(LocalDateTime min,LocalDateTime max);
}
