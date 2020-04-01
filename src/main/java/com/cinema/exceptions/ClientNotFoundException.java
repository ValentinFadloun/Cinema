package com.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientNotFoundException extends ResponseStatusException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException (String id) {
		super(HttpStatus.NOT_FOUND, "Le client id = "+id+" n'existe pas.");
	}
}
