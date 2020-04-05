package com.cinema.services.impl;

/**
 * 
 * @author Valentin Fadloun
 *
 **/

import java.time.LocalDate;
import org.springframework.stereotype.Service;

import com.cinema.models.Assister;
import com.cinema.models.Client;
import com.cinema.models.Seance;
import com.cinema.repositories.AssisterRepository;
import com.cinema.services.AssisterService;
import com.cinema.services.crud.impl.CRUDServiceImpl;

/**
 * 
 * Création de la classe Service pour les Assister qui implément l'interface Assister Service
 *
 */

@Service
public class AssisterServiceImpl extends CRUDServiceImpl<Assister> implements AssisterService {

	/**
	 * Constructeur permettant de donner le repository utilisé au CRUD Général
	 * @param repo
	 */
	public AssisterServiceImpl(AssisterRepository repo) {
		super(repo);
	}

	/**
	 * Methode permettant de fixer le prix du ticket en fonction du client.
	 * La méthode test l'age de la personne, si elle est étudiante et quel type de film elle va voir
	 * @param seance, client, assister
	 */
	@Override
	public void setPrix(Seance seance, Client client, Assister assister) {
		float prix = 10;
		if(LocalDate.now().compareTo(client.getNaissance()) < 10) {
			prix -= 4;
		}else if(client.isEtudient()) {
			prix -= 2;
		}
		if(seance.getType() == "3D") {
			prix += 3;
		} else if (seance.getType() == "IMAX") {
			prix += 6;
		} else if (seance.getType() == "4DX") {
			prix += 8;
		}
		assister.setPrix(prix);
		this.save(assister);
	}
}
