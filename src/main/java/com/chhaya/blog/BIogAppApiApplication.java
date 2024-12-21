package com.chhaya.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BIogAppApiApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(BIogAppApiApplication.class, args);
	}
	
	//bean declaration
	@Bean //spring will create the object of modelMapper class automatically.and we can use it
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

}
