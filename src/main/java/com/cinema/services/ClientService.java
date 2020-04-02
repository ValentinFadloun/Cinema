package com.cinema.services;

import java.util.List;

import com.cinema.models.Client;
import com.cinema.services.crud.CRUDService;

public interface ClientService extends CRUDService<Client>{
	
	public List<Client> findAllByNom(String nom);
	public int findClientAge(Client c);
}
