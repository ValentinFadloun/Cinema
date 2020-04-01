package com.cinema.services;

import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Seance;
import com.cinema.services.crud.CRUDService;

public interface AssisterService extends CRUDService<Assister> {

	/*public Assister save(Assister a);
	public Assister update(Assister a);
	public void delete(String id);
	public List<Assister> findAll();
	public Optional<Assister> findById(String id);*/
	public void setPrix(Seance seance, Client client, Assister assister);
}
