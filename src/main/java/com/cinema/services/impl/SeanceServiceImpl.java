package com.cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinema.exceptions.ClientNotFoundException;
import com.cinema.exceptions.SeanceNotFoundException;
import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Seance;
import com.cinema.repositories.SeanceRepository;
import com.cinema.services.AssisterService;
import com.cinema.services.ClientService;
import com.cinema.services.SeanceService;

@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository repo;
	@Autowired
	private AssisterService assisterService;
	@Autowired
	private ClientService clientService;
	
	@Override
	public Seance save(Seance s) {
		// TODO Auto-generated method stub
		return this.repo.save(s);
	}

	@Override
	public Seance update(Seance s) {
		// TODO Auto-generated method stub
		return this.repo.save(s);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		this.repo.deleteById(id);
	}

	@Override
	public List<Seance> findAll() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public Optional<Seance> findById(String id) {
		// TODO Auto-generated method stub
		return this.repo.findById(id);
	}

	@Override
	public Seance addAssister(String idSeance, String idClient) {
		// TODO Auto-generated method stub
		Optional<Seance> seance = this.findById(idSeance);
		Seance res = null;
		if(seance.isPresent()) {
			List<Assister> listClient = seance.get().getClients();
			Optional<Client> client = clientService.findById(idClient);
			if(client.isPresent()) {
				Assister assister = new Assister();
				assister.setClient(client.get());
				assisterService.setPrix(seance.get(), client.get(), assister);
				listClient.add(assister);
				/*listClient.add(client.get());*/
				seance.get().setClients(listClient);
				this.update(seance.get());
				res = seance.get();
			}else {
				throw new ClientNotFoundException(idClient);
			}
		}else {
			throw new SeanceNotFoundException(idSeance);
		}
		return res;
	}

}
