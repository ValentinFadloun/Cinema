package com.cinema.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Film;

public interface FilmRepository extends MongoRepository<Film, String>{
	public List<Film> findAllByTitre(String titre);
	/*public List<Utilisateur> findAllByAge(int age);
	public List<Utilisateur> findAllByAgeGreaterThan(int age);
	public List<Utilisateur> findAllByAgeAndNom(int age, String nom);
	public boolean findByNomExists(String nom);
	public Optional<Utilisateur> findOneByNom(String nom);*/
}
