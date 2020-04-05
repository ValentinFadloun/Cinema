package com.cinema.services;

/**
 * @author Valentin Fadloun
 */

import com.cinema.models.Commentaire;
import com.cinema.models.Film;
import com.cinema.services.crud.CRUDService;

/**
 * 
 * Création de l'interface Service pour les Commentaires
 * Elle permet de définir les méthodes spécifique a Commentaire qui vont etre utilisé dans la classe services
 *
 */
public interface CommentaireService extends CRUDService<Commentaire>{

	public float filmMoyenne(Film film);
}
