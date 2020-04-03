package com.cinema.repositories.impl;

import java.util.List;

import com.cinema.dto.SeanceDTO;
import com.cinema.models.Seance;

public interface SeanceRepositoryCustom {
	
	
	public List<Seance> findSeanceByDTO(SeanceDTO seance);
}
