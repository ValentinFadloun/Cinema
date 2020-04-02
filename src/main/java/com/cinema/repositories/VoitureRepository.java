package com.cinema.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.cinema.models.Voiture;

public interface VoitureRepository extends ReactiveMongoRepository<Voiture, String>{

}
