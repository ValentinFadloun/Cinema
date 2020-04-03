package com.cinema.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.dto.AssisterDTO;
import com.cinema.dto.SeanceDTO;
import com.cinema.exceptions.ClientForbiddenException;
import com.cinema.exceptions.SeanceForbiddenException;
import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Film;
import com.cinema.models.Salle;
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
	public Seance addClientToSeance(String idSeance, String idClient) {
		Seance res = null;
		if(this.placesSeance(idSeance) > 0) {
			Optional<Seance> seance = this.findById(idSeance);
			if(seance.isPresent()) {
				List<Assister> listClient = seance.get().getClients();
				Optional<Client> client = clientService.findById(idClient);
				if(client.isPresent()) {
					if(clientService.findClientAge(client.get()) > filmService.findAgeLimite(seance.get().getFilm())) {
						Assister assister = new Assister();
						assister.setClient(client.get());
						assisterService.setPrix(seance.get(), client.get(), assister);
						listClient.add(assister);
						seance.get().setClients(listClient);
						this.update(seance.get());
						res = seance.get();
					}else {
						throw new ClientForbiddenException(idClient);
					}
				}else {
					throw new NotFoundException("Le client ",idClient);
				}
			}else {
				throw new NotFoundException("La seance ",idSeance);
			}
		}else {
			throw new SeanceForbiddenException(idSeance);
		}
		return res;
	}
	

	@Override
	public AssisterDTO addClientToSeanceV2(String idSeance, String idClient) {
		AssisterDTO res = new AssisterDTO();
		if(this.placesSeance(idSeance) > 0) {
			Optional<Seance> seance = this.findById(idSeance);
			if(seance.isPresent()) {
				List<Assister> listClient = seance.get().getClients();
				Optional<Client> client = clientService.findById(idClient);
				if(client.isPresent()) {
					if(clientService.findClientAge(client.get()) > filmService.findAgeLimite(seance.get().getFilm())) {
						Assister assister = new Assister();
						assister.setClient(client.get());
						assisterService.setPrix(seance.get(), client.get(), assister);
						listClient.add(assister);
						seance.get().setClients(listClient);
						this.update(seance.get());
						res.setHoraire(seance.get().getDate());
						res.setFilm(seance.get().getFilm());
						res.setPrix(assister.getPrix());
					}else {
						throw new ClientForbiddenException(idClient);
					}
				}else {
					throw new NotFoundException("Le client ",idClient);
				}
			}else {
				throw new NotFoundException("La seance ",idSeance);
			}
		}else {
			throw new SeanceForbiddenException(idSeance);
		}
		return res;
	}

	@Override
	public List<Seance> findAllByFilmNom(String titre) {
		// TODO Auto-generated method stub
		List<Film> listFilm = filmService.findAllByTitre(titre);
		List<Seance> listSeance = new ArrayList<Seance>();
		for (Film f : listFilm) {
			System.out.println(f);
			List<Seance> listSeanceFilm = this.repo.findAllByFilm(f);
			listSeance.addAll(listSeanceFilm);
		}
		return listSeance;
	}
	
	@Override
	public List<Seance> findAllByFilmGenre(String genre) {
		// TODO Auto-generated method stub
		List<Seance> seances = new ArrayList<Seance>();
		List<Film> films = this.filmService.findAllByGenre(genre);
		for (Film film : films) {
			seances.addAll(this.repo.findAllByFilm(film));
		}
		return seances;
	}

	@Override
	public List<Seance> findAllByFilmAge(int age) {
		// TODO Auto-generated method stub
		List<Seance> seances = new ArrayList<Seance>();
		List<Film> films = this.filmService.findAllByAge(age);
		for (Film film : films) {
			seances.addAll(this.repo.findAllByFilm(film));
		}
		return seances;
	}
	
	@Override
	public List<Seance> findAllByFilm(Film f) {
		// TODO Auto-generated method stub
		return this.repo.findAllByFilm(f);
	}
	
	@Override
	public List<Seance> findAllByType(String type) {
		// TODO Auto-generated method stub
		return this.repo.findAllByType(type);
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
		}else {
			throw new NotFoundException("La seance ",id);
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
			}else {
				throw new NotFoundException("La salle ",id);
			}
		}else {
			throw new NotFoundException("La seance ",id);
		}
		return places;
	}

	@Override
	public List<Seance> findAllByCrenauxSeance(LocalDateTime min, LocalDateTime max) {
		// TODO Auto-generated method stub
		
		return this.repo.findAllByDateBetween(min, max);
	}

	@Override
	public List<Seance> findSeanceByDTO(SeanceDTO rechercheSeance) {
		// TODO Auto-generated method stub
		return this.repo.findSeanceByDTO(rechercheSeance);
	}
}
