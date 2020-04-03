package com.cinema.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.cinema.models.Voiture;


//Voiture est la pour me rappeler reactivemongo
public interface VoitureRepository extends ReactiveMongoRepository<Voiture, String>{

}
