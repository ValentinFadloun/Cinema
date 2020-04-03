package com.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException (String classe, String id) {
		super(HttpStatus.NOT_FOUND, classe+"id = "+id+" n'existe pas.");
	}
}
