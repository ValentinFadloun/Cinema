package com.cinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.dto.CinemaDTO;
import com.cinema.models.Cinema;
import com.cinema.services.CinemaService;

@RestController
@CrossOrigin
@RequestMapping("cinemas")
public class CinemaController {
	
	@Autowired
	private CinemaService service;
	
	@PostMapping("cinema")
	public CinemaDTO saveCinema(@RequestBody CinemaDTO cinema) {
		return this.service.saveCinema(cinema);
	}
	
	@GetMapping("")
	public Iterable<Cinema> findAll() {
		return this.service.findAll();
	}
}
