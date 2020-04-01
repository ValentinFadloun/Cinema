package com.cinema.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Film;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Film> findAllByTitre(String titre) {
		// TODO Auto-generated method stub
		return this.repo.findAllByTitre(titre);
	}
}
