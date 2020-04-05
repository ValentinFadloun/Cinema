package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinema.models.Client;
import com.cinema.repositories.ClientRepository;
import com.cinema.services.ClientService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Clients qui implément l'interface Client Service
 *
 */
@Service
public class ClientServiceImpl extends CRUDServiceImpl<Client> implements ClientService {
	
	/*
	 * Déclaration de la variable permettant d'accéeder au Repository de Film
	 */
	@Autowired
	private ClientRepository repo;

	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public ClientServiceImpl(ClientRepository repo) {
		super(repo);
	}
	
	/**
	 * Methode permettant de chercher tout les clients en fonction d'un nom
	 * @param nom
	 * @return List<Client>
	 */
	@Override
	public List<Client> findAllByNom(String nom) {
		return this.repo.findAllByNom(nom);
	}

	/**
	 * Methode permettant de trouver l'age d'un client
	 * @param client
	 * @return int
	 */
	@Override
	public int findClientAge(Client client) {
		return LocalDate.now().compareTo(client.getNaissance());
	}
}
