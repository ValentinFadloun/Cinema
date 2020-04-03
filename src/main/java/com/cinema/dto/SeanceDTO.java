package com.cinema.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceDTO {
	String genre;
	LocalDateTime horraireMin;
	LocalDateTime horraireMax;
	int age;
	String type;
}
