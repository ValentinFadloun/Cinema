package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Client;
import com.cinema.repositories.ClientRepository;
import com.cinema.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository repo;

	@Override
	public Client save(Client c) {
		// TODO Auto-generated method stub
		return this.repo.save(c);
	}

	@Override
	public Client update(Client c) {
		// TODO Auto-generated method stub
		return this.repo.save(c);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Optional<Client> findById(String id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id);
	}

	@Override
	public List<Client> findAllByNom(String nom) {
		// TODO Auto-generated method stub
		return this.repo.findAllByNom(nom);
	}

}
