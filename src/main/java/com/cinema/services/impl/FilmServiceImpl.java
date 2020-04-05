package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.FilmRepository;
import com.cinema.services.CommentaireService;
import com.cinema.services.FilmService;
import com.cinema.services.SeanceService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Films qui implément l'interface Film Service
 *
 */
@Service
public class FilmServiceImpl extends CRUDServiceImpl<Film> implements FilmService{
	
	/*
	 * Déclaration des variables permettant d'accéeder au Repository de Film et aux Services nécessaire
	 */
	@Autowired
	private FilmRepository repo;
	@Autowired
	private SeanceService seanceService;
	@Autowired
	private CommentaireService commentaireService;

	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public FilmServiceImpl(FilmRepository repo) {
		super(repo);
	}

	/**
	 * Méthode permettant de chercher tout les films en fonction de leur titre
	 * @param titre
	 * @return List<Film>
	 */
	@Override
	public List<Film> findAllByTitre(String titre) {
		return this.repo.findAllByTitre(titre);
	}
	
	/**
	 * Méthode permettant de chercher tout les films en fonction de leur genre
	 * @param genre
	 * @return List<Film>
	 */
	@Override
	public List<Film> findAllByGenre(String genre) {
		return this.repo.findAllByGenre(genre);
	}

	/**
	 * Méthode permettant calculer la recette d'un film.
	 * Elle va regarder pour un film donné (id) toutes les seances lui correspondant et faire la somme des recettes des seances en question
	 * @param id
	 * @return float
	 */
	@Override
	public float recetteFilm(String id) {
		Optional<Film> monFilm = this.repo.findById(id);
		float recette = 0F;
		if(monFilm.isPresent()) {
			List<Seance> listSeance = this.seanceService.findAllByFilm(monFilm.get());
			for (Seance seance : listSeance) {
				recette += this.seanceService.recetteSeance(seance.getId());
			}
		}else {
			throw new NotFoundException("Le film ",id);
		}
		return recette;
	}

	/**
	 * Méthode permettant de trouver l'age limite autorisé d'un film
	 * @param film
	 * @return int
	 */
	@Override
	public int findAgeLimite(Film film) {
		return film.getAgeLimite();
	}

	/**
	 * Méthode permettant de chercher tout les films qui on un age limite inférieur a l'age demandé
	 * @param age
	 * @return List<Film>
	 */
	@Override
	public List<Film> findAllByAge(int age) {
		return this.repo.findAllByAgeLimiteLessThan(age);
	}

	/**
	 * Méthode permettant d'ajouter un commentaire a un film
	 * @param commentaire
	 * @return Commentaire
	 */
	@Override
	public Commentaire addCommentaire(Commentaire commentaire) {
		return this.commentaireService.save(commentaire);
	}
	
	/**
	 * Méthode permettant de faire un trie a bulle dans l'ordre décroissant (plus grand au plus petit).
	 * Elle sert notamment pour trier les films en fonction de leurs note moyenne
	 * @param listFilm, listMoyenne
	 * @return List<Film>
	 */
	@Override
	public List<Film> bubbleSort(List<Film> listFilm, List<Float> listMoyenne){
		boolean fini = true;
		while (fini) {
			fini = false;
			for(int i = 0; i < listFilm.size()-1; i++) {
				if(listMoyenne.get(i) < listMoyenne.get(i+1)) {
					fini = true;
					Film memoireFilm = listFilm.get(i);
					listFilm.set(i, listFilm.get(i+1));
					listFilm.set(i+1, memoireFilm);
					float memoireMoyenne = listMoyenne.get(i);
					listMoyenne.set(i, listMoyenne.get(i+1));
					listMoyenne.set(i+1, memoireMoyenne);
				}
			}
		}
		return listFilm;
	}

	/**
	 * Méthode permettant de trier une liste de film par note moyenne
	 * @param listFilm
	 * @return List<Film>
	 */
	@Override
	public List<Film> sortAllByNote(List<Film> listFilm) {
		List<Float> listMoyenne = new ArrayList<Float>();
		for(Film film : listFilm) {
			listMoyenne.add(this.commentaireService.filmMoyenne(film));
		}
		listFilm = this.bubbleSort(listFilm, listMoyenne);
		return listFilm;
	}

	/**
	 * Méthode permettant de chercher tout les films en fonction de leur titre et trié par note moyenne
	 * @param titre
	 * @return List<Film>
	 */
	@Override
	public List<Film> findAllByTitreSortByNote(String titre) {
		return this.sortAllByNote(this.findAllByTitre(titre));
	}

	/**
	 * Méthode permettant de chercher tout les films trié par note moyenne
	 * @return List<Film>
	 */
	@Override
	public List<Film> sortAllFilmByNote() {
		return this.sortAllByNote((List<Film>) this.findAll());
	}
}
