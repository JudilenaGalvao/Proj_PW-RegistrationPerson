package com.example.ProjetoPw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@SpringBootApplication
@ServletComponentScan
public class ProjetoPwApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPwApplication.class, args);
	}

}
