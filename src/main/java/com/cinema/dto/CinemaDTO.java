package com.cinema.dto;

import java.util.List;

import com.cinema.models.Cinema;
import com.cinema.models.Salle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {

	Cinema cinema;
	List<Salle> salles;
}
