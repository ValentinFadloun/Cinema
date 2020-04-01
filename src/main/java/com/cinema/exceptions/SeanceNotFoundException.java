package com.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeanceNotFoundException extends ResponseStatusException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeanceNotFoundException (String id) {
		super(HttpStatus.NOT_FOUND, "La seance id = "+id+" n'existe pas.");
	}
}
