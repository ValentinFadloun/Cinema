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

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.services.FilmService;

/**
 * 
 * Création de la classe Controller pour les Films qui permet de définir les routes de l'api
 *
 */
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
	
	@DeleteMapping("")
	public void deleteFilm(@RequestBody Film f) {
		this.service.delete(f);
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
	
	@GetMapping("titre/{titre}/note")
	public List<Film> findAllByTitreSortByNote(@PathVariable String titre){
		return this.service.findAllByTitreSortByNote(titre);
	}
	
	@GetMapping("{id}/recette")
	public float recetteFilm(@PathVariable String id) {
		return this.service.recetteFilm(id);
	}
	
	@PostMapping("/commentaire")
	public Commentaire addCommentaire(@RequestBody Commentaire commentaire) {
		return this.service.addCommentaire(commentaire);
	}
	
	@GetMapping("/commentaire/moyenne")
	public List<Film> getMoyenne(@RequestBody List<Film> listFilm) {
		return this.service.sortAllByNote(listFilm);
	}
	
	@GetMapping("/commentaire/moyenne/all")
	public List<Film> findAllSortByNote() {
		return this.service.sortAllFilmByNote();
	}
}
