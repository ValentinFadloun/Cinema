package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Film;
import com.cinema.repositories.AssisterRepository;
import com.cinema.repositories.FilmRepository;
import com.cinema.services.FilmService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class FilmServiceImpl extends CRUDServiceImpl<Film> implements FilmService{
	
	@Autowired
	private FilmRepository repo;

	public FilmServiceImpl(FilmRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
	/*
	@Override
	public Film save(Film f) {
		// TODO Auto-generated method stub
		return this.repo.save(f);
	}

	@Override
	public Film update(Film f) {
		// TODO Auto-generated method stub
		return this.repo.save(f);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
	}

	@Override
	public List<Film> findAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Optional<Film> findById(String id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id);
	}*/

	@Override
	public List<Film> findAllByTitre(String titre) {
		// TODO Auto-generated method stub
		return this.repo.findAllByTitre(titre);
	}
}
