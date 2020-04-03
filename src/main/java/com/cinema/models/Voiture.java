package com.cinema.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

//Voiture est la pour me rappeler reactivemongo

@Data
@Document
public class Voiture {

	@Id
	private String id;
	private String nom;
}
