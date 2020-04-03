package com.cinema.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cinema.models.Commentaire;

public interface CommentaireRepository extends MongoRepository<Commentaire, String>{

}
