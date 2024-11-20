package com.programacionavanzadaii.trabajoPractico1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.programacionavanzadaii.trabajoPractico1.mappers")
public class TrabajoPractico1Application {

	public static void main(String[] args) {
		SpringApplication.run(TrabajoPractico1Application.class, args);
	}

}
