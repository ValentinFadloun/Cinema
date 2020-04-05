package com.cinema.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;

public interface CommentaireRepository extends MongoRepository<Commentaire, String>{

	public List<Commentaire> findAllByFilm(Film f);
}
