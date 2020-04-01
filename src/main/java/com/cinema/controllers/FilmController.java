package com.cinema.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.models.Film;
import com.cinema.services.FilmService;

@RestController
@CrossOrigin
@RequestMapping("films")
public class FilmController {
	
	@Autowired
	private FilmService service;
	
	@PostMapping("")
	public Film saveFilm(@RequestBody Film entity) {
		return this.service.save(entity);
	}
	
	@PutMapping("")
	public Film updateFilm(@RequestBody Film f) {
		return this.service.update(f);
	}
	
	@DeleteMapping("{id}")
	public void deleteFilm(@PathVariable String id) {
		this.service.delete(id);
	}
	
	@GetMapping("")
	public Iterable<Film> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Film> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@GetMapping("titre/{titre}")
	public List<Film> findAllByTitre(@PathVariable String titre){
		return this.service.findAllByTitre(titre);
	}
	
	@GetMapping("{id}/recette")
	public int recetteFilm(@PathVariable String id) {
		
		return 0;
	}
}