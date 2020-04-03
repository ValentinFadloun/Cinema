package com.cinema.dto;

import java.time.LocalDateTime;

import com.cinema.models.Film;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssisterDTO {

	float prix;
	Film film;
	LocalDateTime horaire;
}
