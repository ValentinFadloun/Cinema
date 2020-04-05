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

@Service
public class FilmServiceImpl extends CRUDServiceImpl<Film> implements FilmService{
	
	@Autowired
	private FilmRepository repo;
	@Autowired
	private SeanceService seanceService;
	@Autowired
	private CommentaireService commentaireService;

	public FilmServiceImpl(FilmRepository repo) {
		super(repo);
	}

	@Override
	public List<Film> findAllByTitre(String titre) {
		return this.repo.findAllByTitre(titre);
	}
	
	@Override
	public List<Film> findAllByGenre(String genre) {
		return this.repo.findAllByGenre(genre);
	}

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

	@Override
	public int findAgeLimite(Film f) {
		return f.getAgeLimite();
	}

	@Override
	public List<Film> findAllByAge(int age) {
		return this.repo.findAllByAgeLimiteLessThan(age);
	}

	@Override
	public Commentaire addCommentaire(Commentaire commentaire) {
		return this.commentaireService.save(commentaire);
	}
	
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

	@Override
	public List<Film> sortAllByNote(List<Film> listFilm) {
		List<Float> listMoyenne = new ArrayList<Float>();
		for(Film film : listFilm) {
			listMoyenne.add(this.commentaireService.filmMoyenne(film));
		}
		listFilm = this.bubbleSort(listFilm, listMoyenne);
		return listFilm;
	}

	@Override
	public List<Film> findAllByTitreSortByNote(String titre) {
		return this.sortAllByNote(this.findAllByTitre(titre));
	}

	@Override
	public List<Film> sortAllFilmByNote() {
		return this.sortAllByNote((List<Film>) this.findAll());
	}
	
	
}
