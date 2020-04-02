package com.cinema.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.models.Seance;
import com.cinema.services.SeanceService;

@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {

	@Autowired
	private SeanceService service;
	
	@PostMapping("")
	public Seance saveSeance(@RequestBody Seance entity) {
		return this.service.save(entity);
	}
	
	@PutMapping("")
	public Seance updateSeance(@RequestBody Seance s) {
		return this.service.update(s);
	}
	
	@DeleteMapping("")
	public void deleteSeance(@RequestBody Seance s) {
		this.service.delete(s);
	}
	
	@DeleteMapping("{id}")
	public void deleteSeance(@PathVariable String id) {
		this.service.delete(id);
	}
	
	@GetMapping("")
	public Iterable<Seance> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Seance> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@PostMapping("{id}/assister/{uid}")
	public void addAssister(@PathVariable String id,@PathVariable String uid) {
		this.service.addClientToSeance(id, uid);
	}
	
	@GetMapping("film/{titre}")
	public List<Seance> findAllByFilmNom(@PathVariable String titre){
		return this.service.findAllByFilmNom(titre);
	}
	
	@GetMapping("{id}/recette")
	public float recetteSeance(@PathVariable String id){
		return this.service.recetteSeance(id);
	}

	@GetMapping("{id}/places")
	public int placesSeance(@PathVariable String id){
		return this.service.placesSeance(id);
	}
	
	@GetMapping("horaire/{min}/{max}")
	public List<Seance> findAllByCrenauxSeance(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime min, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime max){
		return this.service.findAllByCrenauxSeance(min,max);
	}
}
