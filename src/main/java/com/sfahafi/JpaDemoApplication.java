package com.sfahafi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Ejemplos de Spring Data JPA");
		guardar();
		eliminar();
		
	}
	
	
	private void guardar() {
		System.out.println("Insertando un registro");
	}
	
	private void eliminar() {
		System.out.println("Eliminando un registro");
	}
	

}
