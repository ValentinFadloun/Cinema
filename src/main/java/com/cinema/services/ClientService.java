package com.cinema.services;

/**
 * @author Valentin Fadloun
 */

import java.util.List;

import com.cinema.models.Client;
import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de l'interface Service pour les Clients
 * Elle permet de définir les méthodes spécifique a Client qui vont etre utilisé dans la classe services
 *
 */
public interface ClientService extends CRUDService<Client>{
	
	public List<Client> findAllByNom(String nom);
	public int findClientAge(Client client);
}
