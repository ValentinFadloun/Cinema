package com.cinema.repositories.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cinema.dto.SeanceDTO;
import com.cinema.models.Film;
import com.cinema.models.Seance;

public class SeanceRepositoryCustomImpl implements SeanceRepositoryCustom {

	@Autowired
	private MongoTemplate template;
	
	/*
	 * Methode permettant de faire une recherche dans la Base de Donnée
	 * Elle peux chercher un fonction du genre et de l'age limite du film, de la plage horaire de la seance et du type de seance
	 * Elle crée un requette a la Base de Donne sur la quel viens s'ajouter des critère en fonction de la présence ou non de ces derniers.
	 * @param seance
	 * @return List<Seance>
	 */
	@Override
	public List<Seance> findSeanceByDTO(SeanceDTO seance) {
		// TODO Auto-generated method stub
		Query queryFilm = new Query();
		if(seance.getGenre() != null && seance.getGenre() != "") {
			queryFilm.addCriteria(Criteria.where("genre").is(seance.getGenre()));
		}

		if(seance.getAge() != 0) {
			queryFilm.addCriteria(Criteria.where("ageLimite").lte(seance.getAge()));
		}
		List<Film> listFilm = new ArrayList<Film>();
		listFilm = this.template.find(queryFilm, Film.class);
		
		Query query = new Query();
		if(seance.getHorraireMax() != null && seance.getHorraireMin() != null) {
			query.addCriteria(Criteria.where("date").lte(seance.getHorraireMax()).and("date").gte(seance.getHorraireMin()));
		}else if(seance.getHorraireMax() != null) {
			query.addCriteria(Criteria.where("date").lte(seance.getHorraireMax()).and("date").gte(LocalDateTime.now()));
		}else if(seance.getHorraireMin() != null) {
			query.addCriteria(Criteria.where("date").gte(seance.getHorraireMin()));
		}
		
		if(seance.getType() != null && seance.getType() != "") {
			query.addCriteria(Criteria.where("type").lte(seance.getType()));
		}
		
		if(!listFilm.isEmpty()) {
			List<Film> mesFilms = new ArrayList<Film>();
			for(Film f : listFilm) {
				mesFilms.add(f);
			}
			query.addCriteria(Criteria.where("film").in(mesFilms));
		}
		List<Seance> listSeance = new ArrayList<Seance>();
		listSeance = this.template.find(query, Seance.class);
		return listSeance;
	}

}
