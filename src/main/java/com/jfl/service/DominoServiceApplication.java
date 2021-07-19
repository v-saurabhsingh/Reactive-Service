package com.jfl.service;

import com.jfl.service.aws.SesService;
import com.jfl.service.aws.modal.EmailSendRequest;
import com.jfl.service.r2dbc.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

import java.util.List;

@Slf4j
@SpringBootApplication
public class DominoServiceApplication implements CommandLineRunner {

	@Autowired
	private SesService sesService;
	@Autowired
	private R2dbcEntityTemplate r2dbcEntityTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DominoServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		sesService.sendMail(EmailSendRequest.builder()
				.source("saurabh.singh.corepeelers@gmail.com")
				.addresses(List.of("singhsaurabh920@gmail.com"))
				.subject("Google")
				.textData("Hi, Maya ......")
				.build()).whenCompleteAsync((res,ex)->{
					if (ex==null) {
						log.info(res.toString());
					}else {
						log.error("Exception sending Email: ",ex);
					}
		});
	}
}
