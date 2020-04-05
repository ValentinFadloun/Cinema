package com.cinema.services;

/**
 * @author Valentin Fadloun
 */
import java.util.List;

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de l'interface Service pour les Films
 * Elle permet de définir les méthodes spécifique a Film qui vont etre utilisé dans la classe services
 *
 */
public interface FilmService extends CRUDService<Film>{
	
	public List<Film> findAllByTitre(String titre);
	public List<Film> findAllByGenre(String genre);
	public List<Film> findAllByAge(int age);
	public float recetteFilm(String id);
	public int findAgeLimite(Film film);
	public Commentaire addCommentaire(Commentaire commentaire);
	public List<Film> sortAllByNote(List<Film> listFilm);
	public List<Film> bubbleSort(List<Film> listFilm, List<Float> listMoyenne);
	public List<Film> findAllByTitreSortByNote(String titre);
	public List<Film> sortAllFilmByNote();
}
