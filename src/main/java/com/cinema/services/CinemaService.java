package com.cinema.services;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.services.crud.CRUDService;

public interface CinemaService extends CRUDService<Cinema> {
	
	public CinemaDTO saveCinema(CinemaDTO cinema);
}
