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
	public Seance updateSeance(@RequestBody Seance c) {
		return this.service.update(c);
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
		this.service.addAssister(id, uid);
	}
	
	@GetMapping("film/{titre}")
	public List<Seance> findByFilmNom(@PathVariable String titre){
		return this.service.findByFilmNom(titre);
	}
}
