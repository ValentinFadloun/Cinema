package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.FilmRepository;
import com.cinema.services.FilmService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class FilmServiceImpl extends CRUDServiceImpl<Film> implements FilmService{
	
	@Autowired
	private FilmRepository repo;
	@Autowired
	private SeanceServiceImpl seanceService;

	public FilmServiceImpl(FilmRepository repo) {
		super(repo);
	}

	@Override
	public List<Film> findAllByTitre(String titre) {
		// TODO Auto-generated method stub
		return this.repo.findAllByTitre(titre);
	}

	@Override
	public float recetteFilm(String id) {
		// TODO Auto-generated method stub
		Optional<Film> monFilm = this.repo.findById(id);
		float recette = 0F;
		if(monFilm.isPresent()) {
			List<Seance> listSeance = this.seanceService.findAllByFilm(monFilm.get());
			for (Seance seance : listSeance) {
				recette += this.seanceService.recetteSeance(seance.getId());
			}
		}
		return recette;
	}

	@Override
	public int findAgeLimite(Film f) {
		// TODO Auto-generated method stub
		return f.getAgeLimite();
	}
}
