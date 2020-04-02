package com.cinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Voiture;
import com.cinema.repositories.VoitureRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VoitureService {

	@Autowired
	VoitureRepository repo;
	
	public Flux<Voiture> findAll() throws InterruptedException{
		Flux<Object> flux = Flux.just("");
		flux.subscribe((e) -> System.out.println(e));
		flux.map((o) -> o.toString()+"!!!").subscribe((o) -> System.out.println(o));
		return this.repo.findAll();
	}
	
	public Mono<Voiture> findById(String id){
		return this.repo.findById(id);
	}
}
