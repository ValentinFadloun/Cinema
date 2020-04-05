package com.cinema.services;

/**
 * @author Valentin Fadloun
 */

import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Seance;
import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de l'interface Service pour les Assisters
 * Elle permet de définir les méthodes spécifique a Assister qui vont etre utilisé dans la classe services
 *
 */
public interface AssisterService extends CRUDService<Assister> {

	public void setPrix(Seance seance, Client client, Assister assister);
}
