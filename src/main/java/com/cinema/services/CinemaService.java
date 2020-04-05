package com.cinema.services;

/**
 * @author Valentin Fadloun
 */

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de l'interface Service pour les Cinemas.
 * Elle permet de définir les méthodes spécifique a Cinema qui vont etre utilisé dans la classe services
 *
 */
public interface CinemaService extends CRUDService<Cinema> {
	
	public CinemaDTO saveCinema(CinemaDTO cinema);
}
