package com.cinema.services;

import java.util.List;
import java.util.Optional;

import com.cinema.models.Client;

public interface ClientService {
	
	public Client save(Client c);
	public Client update(Client c);
	public void delete(String id);
	public List<Client> findAll();
	public Optional<Client> findById(String id);
	public List<Client> findAllByNom(String nom);
}
