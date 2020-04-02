package com.cinema.services.impl;

import org.springframework.stereotype.Service;

import com.cinema.models.Salle;
import com.cinema.repositories.SalleRepository;
import com.cinema.services.SalleService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class SalleServiceImpl extends CRUDServiceImpl<Salle> implements SalleService{

	public SalleServiceImpl(SalleRepository repo) {
		super(repo);
	}
}
