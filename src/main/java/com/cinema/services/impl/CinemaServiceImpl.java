package com.cinema.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.models.Salle;
import com.cinema.repositories.CinemaRepository;
import com.cinema.services.CinemaService;
import com.cinema.services.SalleService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class CinemaServiceImpl extends CRUDServiceImpl<Cinema> implements CinemaService {
	
	@Autowired
	private CinemaRepository repo;
	@Autowired
	private SalleService salleService;

	public CinemaServiceImpl(CinemaRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CinemaDTO saveCinema(CinemaDTO cinema) {
		// TODO Auto-generated method stub
		this.repo.save(cinema.getCinema());
		List<Salle> salles = cinema.getSalles();
		List<Salle> newSalles = new ArrayList<Salle>();
		for (Salle salle : salles) {
			salle.setCinema(cinema.getCinema());
			newSalles.add(salle);
			this.salleService.save(salle);
		}
		cinema.setSalles(newSalles);
		return cinema;
	}

}
