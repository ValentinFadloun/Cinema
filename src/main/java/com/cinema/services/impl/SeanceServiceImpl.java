package com.cinema.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cinema.exceptions.ClientNotFoundException;
import com.cinema.exceptions.SeanceNotFoundException;
import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Film;
import com.cinema.models.Seance;
import com.cinema.repositories.SeanceRepository;
import com.cinema.services.AssisterService;
import com.cinema.services.ClientService;
import com.cinema.services.FilmService;
import com.cinema.services.SeanceService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

@Service
public class SeanceServiceImpl extends CRUDServiceImpl<Seance> implements SeanceService {

	@Autowired
	private AssisterService assisterService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private SeanceRepository repo;
	
	public SeanceServiceImpl(SeanceRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
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

	@Override
	public List<Seance> findByFilmNom(String titre) {
		// TODO Auto-generated method stub
		List<Film> listFilm = filmService.findAllByTitre(titre);
		List<Seance> listSeance = new ArrayList<Seance>();
		for (Film f : listFilm) {
			List<Seance> listSeanceFilm = this.repo.findAllByFilm(f);
			listSeance.addAll(listSeanceFilm);
		}
		return listSeance;
	}

}
