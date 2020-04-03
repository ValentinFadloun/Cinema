package com.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClientForbiddenException extends ResponseStatusException  {
	
	private static final long serialVersionUID = 1L;

	public ClientForbiddenException (String id) {
		super(HttpStatus.FORBIDDEN, "Le client id = "+id+" n'a pas l'age minimum requis.");
	}
}
