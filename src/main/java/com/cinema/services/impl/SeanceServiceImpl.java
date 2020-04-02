package com.cinema.services.impl;

import java.time.LocalDateTime;
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
import com.cinema.models.Salle;
import com.cinema.models.Seance;
import com.cinema.repositories.SeanceRepository;
import com.cinema.services.AssisterService;
import com.cinema.services.ClientService;
import com.cinema.services.FilmService;
import com.cinema.services.SalleService;
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
	private SalleService salleService;
	@Autowired
	private SeanceRepository repo;
	
	public SeanceServiceImpl(SeanceRepository repo) {
		super(repo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Seance addClientToSeance(String idSeance, String idClient) {
		Seance res = null;
		if(this.placesSeance(idSeance) > 0) {
			Optional<Seance> seance = this.findById(idSeance);
			if(seance.isPresent()) {
				List<Assister> listClient = seance.get().getClients();
				Optional<Client> client = clientService.findById(idClient);
				if(client.isPresent() && clientService.findClientAge(client.get()) > filmService.findAgeLimite(seance.get().getFilm())) {
					Assister assister = new Assister();
					assister.setClient(client.get());
					assisterService.setPrix(seance.get(), client.get(), assister);
					listClient.add(assister);
					seance.get().setClients(listClient);
					this.update(seance.get());
					res = seance.get();
				}else {
					throw new ClientNotFoundException(idClient);
				}
			}else {
				throw new SeanceNotFoundException(idSeance);
			}
		}
		return res;
	}

	@Override
	public List<Seance> findAllByFilmNom(String titre) {
		// TODO Auto-generated method stub
		List<Film> listFilm = filmService.findAllByTitre(titre);
		List<Seance> listSeance = new ArrayList<Seance>();
		for (Film f : listFilm) {
			List<Seance> listSeanceFilm = this.repo.findAllByFilm(f);
			listSeance.addAll(listSeanceFilm);
		}
		return listSeance;
	}
	
	@Override
	public List<Seance> findAllByFilm(Film f) {
		// TODO Auto-generated method stub
		return this.repo.findAllByFilm(f);
	}

	@Override
	public float recetteSeance(String id) {
		// TODO Auto-generated method stub
		Optional<Seance> maSeance = this.findById(id);
		float recette = 0F;
		if(maSeance.isPresent()) {
			for(Assister assister : maSeance.get().getClients()) {
				recette += assister.getPrix();
			}
		}
		return recette;
	}

	@Override
	public int placesSeance(String id) {
		// TODO Auto-generated method stub
		Optional<Seance> maSeance = this.repo.findById(id);
		int places = 0;
		if(maSeance.isPresent()) {
			Optional<Salle> maSalle = Optional.ofNullable(maSeance.get().getSalle());
			if(maSalle.isPresent()) {
				places = maSalle.get().getPlace();
				for (int i = 0; i < maSeance.get().getClients().size(); i++) {
					places -= 1;
				}
			}
		}
		return places;
	}

	@Override
	public List<Seance> findAllByCrenauxSeance(LocalDateTime min, LocalDateTime max) {
		// TODO Auto-generated method stub
		
		return this.repo.findAllByDateBetween(min, max);
	}
}
