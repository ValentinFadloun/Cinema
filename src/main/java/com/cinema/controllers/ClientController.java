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

import com.cinema.models.Client;
import com.cinema.services.ClientService;

/**
 * 
 * Création de la classe Controller pour les Clients qui permet de définir les routes de l'api
 *
 */
@RestController
@CrossOrigin
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping("")
	public Client saveClient(@RequestBody Client entity) {
		return this.service.save(entity);
	}
	
	@PutMapping("")
	public Client updateClient(@RequestBody Client c) {
		return this.service.update(c);
	}
	
	@DeleteMapping("")
	public void deleteClient(@RequestBody Client c) {
		this.service.delete(c);
	}
	
	@DeleteMapping("{id}")
	public void deleteClient(@PathVariable String id) {
		this.service.delete(id);
	}
	
	@GetMapping("")
	public Iterable<Client> findAll() {
		return this.service.findAll();
	}
	
	@GetMapping("{id}")
	public Optional<Client> findById(@PathVariable String id) {
		return this.service.findById(id);
	}
	
	@GetMapping("nom/{nom}")
	public List<Client> findAllByNom(@PathVariable String nom){
		return this.service.findAllByNom(nom);
	}
}
