package com.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SeanceForbiddenException extends ResponseStatusException  {
	
	private static final long serialVersionUID = 1L;

	public SeanceForbiddenException (String id) {
		super(HttpStatus.FORBIDDEN, "La seance id = "+id+" n'a plus de places désolé.");
	}
}
