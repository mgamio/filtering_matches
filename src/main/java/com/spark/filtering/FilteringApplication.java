package com.spark.filtering;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spark.filtering.model.Matches;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FilteringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilteringApplication.class, args);
	}

	//If matches.json change, we can redeploy the application
	@Bean
	public Matches matches() throws  Exception{
		ObjectMapper objectMapper = new ObjectMapper();

		//read json file and convert to matches object
		Matches matches = objectMapper.readValue(new File("matches.json"), Matches.class);

		return matches;
	}



}
