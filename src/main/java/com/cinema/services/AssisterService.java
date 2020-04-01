package com.cinema.services;

import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Seance;
import com.cinema.services.crud.CRUDService;

public interface AssisterService extends CRUDService<Assister> {

	public void setPrix(Seance seance, Client client, Assister assister);
}
