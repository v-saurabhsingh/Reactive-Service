package com.jfl.service;

import com.jfl.service.r2dbc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

@Slf4j
@SpringBootApplication
public class DominoServiceApplication implements CommandLineRunner {

	@Autowired
	private R2dbcEntityTemplate r2dbcEntityTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DominoServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
