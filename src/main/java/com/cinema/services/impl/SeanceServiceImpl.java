package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

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

/**
 * 
 * Création de la classe Service pour les Seances qui implément l'interface Seance Service
 *
 */
@Service
public class SeanceServiceImpl extends CRUDServiceImpl<Seance> implements SeanceService {

	/*
	 * Déclaration des variables permettant d'accéeder au Repository de Seance et aux Services nécessaire
	 */
	@Autowired
	private SeanceRepository repo;
	@Autowired
	private AssisterService assisterService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private FilmService filmService;
	
	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public SeanceServiceImpl(SeanceRepository repo) {
		super(repo);
	}

	/**
	 * Méthode permettant d'ajouter un client a une seance.
	 * Elle va verifier si la seance existe puis si elle a encore des places,
	 * Elle va ensuite cherche le client et verifier si il existe ainsi que si il a l'age minimum necessaire pour regarder le film
	 * Enfin elle ajoute le client en lui definissant un prix de seance 
	 * Retourne une seance et est donc une ancienne version (on ne veux pas afficher toute le seance)
	 * @deprecated -> addClientToSeanceV2
	 * @param idSeance, idClient
	 * @return Seance
	 */
	@Override
	public Seance addClientToSeance(String idSeance, String idClient) {
		Seance res = null;
		Optional<Seance> seance = this.findById(idSeance);
		if(seance.isPresent()) {
			if(this.placesSeance(idSeance) > 0) {
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
				throw new SeanceForbiddenException(idSeance);
			}
		}else {
			throw new NotFoundException("La seance ",idSeance);
		}
		return res;
	}
	
	/**
	 * Méthode permettant d'ajouter un client a une seance.
	 * Elle va verifier si la seance existe puis si elle a encore des places,
	 * Elle va ensuite cherche le client et verifier si il existe ainsi que si il a l'age minimum necessaire pour regarder le film
	 * Enfin elle ajoute le client en lui definissant un prix de seance 
	 * Puis crée un objet assister DTO permettant de ne renvoyer que les informations necessaire
	 * Nouvelle methode remplacant addClientToSeance
	 * @param idSeance, idClient
	 * @return AssisterDTO
	 */
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
						this.assisterService.setPrix(seance.get(), client.get(), assister);
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

	/**
	 * Méthode permettant de chercher toutes les seances en fonction du titre de leurs film
	 * Elle va chercher tout les films dont le titre est celui demandé puis cherche les seances avec ces films
	 * @param titre
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByFilmNom(String titre) {
		List<Film> listFilm = filmService.findAllByTitre(titre);
		List<Seance> listSeance = new ArrayList<Seance>();
		for (Film film : listFilm) {
			List<Seance> listSeanceFilm = this.repo.findAllByFilm(film);
			listSeance.addAll(listSeanceFilm);
		}
		return listSeance;
	}
	
	/**
	 * Méthode permettant de chercher toutes les seances en fonction du genre de leurs film
	 * Elle va chercher tout les films dont le genre est celui demandé puis cherche les seances avec ces films
	 * @param genre
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByFilmGenre(String genre) {
		List<Seance> seances = new ArrayList<Seance>();
		List<Film> films = this.filmService.findAllByGenre(genre);
		for (Film film : films) {
			seances.addAll(this.repo.findAllByFilm(film));
		}
		return seances;
	}

	/**
	 * Méthode permettant de chercher toutes les seances en fonction de l'age limite de leurs film
	 * Elle va chercher tout les films dont l'age limite correspond celui demandé puis cherche les seances avec ces films
	 * @param age
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByFilmAge(int age) {
		List<Seance> seances = new ArrayList<Seance>();
		List<Film> films = this.filmService.findAllByAge(age);
		for (Film film : films) {
			seances.addAll(this.repo.findAllByFilm(film));
		}
		return seances;
	}
	
	/**
	 * Méthode permettant de chercher toutes les seances en fonction d'un film
	 * @param film
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByFilm(Film film) {
		return this.repo.findAllByFilm(film);
	}
	
	/**
	 * Méthode permettant de chercher toutes les seances en fonction du type de seance
	 * @param type
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByType(String type) {
		return this.repo.findAllByType(type);
	}

	/**
	 * Méthode permettant trouver les recettes d'une seance
	 * Elle parcours toutes les personnes qui ont assisté a la seance et fais la somme du prix de leur billet
	 * @param id
	 * @return float
	 */
	@Override
	public float recetteSeance(String id) {
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

	/**
	 * Méthode permettant de chercher les places restante pour un seance donnée.
	 * Elle test si la seance existe bien puis va chercher la salle qui lui correspond en verifiant son existance
	 * Enfin elle parcours le nombre de places déja acheter et le retire au nombre total de places de la salle
	 * @param id
	 * @return int
	 */
	@Override
	public int placesSeance(String id) {
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

	/**
	 * Méthode permettant de chercher toutes les seances en fonction d'un creneau horaire
	 * @param min, max
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findAllByCrenauxSeance(LocalDateTime min, LocalDateTime max) {
		return this.repo.findAllByDateBetween(min, max);
	}

	/**
	 * Méthode permettant de chercher toutes les seances en fonction de différents critères
	 * @param rechercheSeance
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findSeanceByDTO(SeanceDTO rechercheSeance) {
		return this.repo.findSeanceByDTO(rechercheSeance);
	}
}
