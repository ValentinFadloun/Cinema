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

import com.cinema.dto.AssisterDTO;
import com.cinema.dto.SeanceDTO;
import com.cinema.models.Seance;
import com.cinema.services.SeanceService;

/**
 * 
 * Création de la classe Controller pour les Seances qui permet de définir les routes de l'api
 *
 */
@RestController
@CrossOrigin
@RequestMapping("seances")
public class SeanceController {

	@Autowired
	private SeanceService service;
	
	@PostMapping("")
	public Seance saveSeance(@RequestBody Seance seance) {
		return this.service.save(seance);
	}
	
	@PutMapping("")
	public Seance updateSeance(@RequestBody Seance seance) {
		return this.service.update(seance);
	}
	
	@DeleteMapping("")
	public void deleteSeance(@RequestBody Seance seance) {
		this.service.delete(seance);
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
	public AssisterDTO addAssister(@PathVariable String id,@PathVariable String uid) {
		return this.service.addClientToSeanceV2(id, uid);
	}
	
	@GetMapping("film/{titre}")
	public List<Seance> findAllByFilmNom(@PathVariable String titre){
		return this.service.findAllByFilmNom(titre);
	}
	
	@GetMapping("film/genre/{genre}")
	public List<Seance> findAllByFilmGenre(@PathVariable String genre){
		return this.service.findAllByFilmGenre(genre);
	}
	
	@GetMapping("film/age/{age}")
	public List<Seance> findAllByFilmGenre(@PathVariable int age){
		return this.service.findAllByFilmAge(age);
	}
	
	@GetMapping("type/{type}")
	public List<Seance> findAllByType(@PathVariable String type){
		return this.service.findAllByType(type);
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
	
	@GetMapping("chercher")
	public List<Seance> findSeanceByDTO(@RequestBody SeanceDTO rechercheSeance){
		return this.service.findSeanceByDTO(rechercheSeance);
	}
}
