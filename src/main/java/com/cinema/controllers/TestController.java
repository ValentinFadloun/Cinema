package com.cinema.controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.models.Seance;

@RestController
@CrossOrigin
@RequestMapping("")
public class TestController {
	@GetMapping("")
	public LocalDateTime findAll() {
		return LocalDateTime.now();
	}
}
