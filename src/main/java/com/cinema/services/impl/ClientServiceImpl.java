package com.cinema.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Client;
import com.cinema.repositories.ClientRepository;
import com.cinema.services.ClientService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class ClientServiceImpl extends CRUDServiceImpl<Client> implements ClientService {
	
	@Autowired
	private ClientRepository repo;

	public ClientServiceImpl(ClientRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Client> findAllByNom(String nom) {
		// TODO Auto-generated method stub
		return this.repo.findAllByNom(nom);
	}

	@Override
	public int findClientAge(Client c) {
		return LocalDate.now().compareTo(c.getNaissance());
	}
}
