package com.sfahafi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sfahafi.model.Categoria;
import com.sfahafi.repository.I_CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
	
	@Autowired
	private I_CategoriasRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {  // psvm seria como la clase principal ejecutable en una aplicacion de escritorio
		guardarTodas();	
		
	}
	
	private void guardarTodas() {
		 
	}
	
	private void existeId() {
		boolean existe = cr.existsById(5);
		System.out.println("Existe: " + existe);
	}
	
	private void buscarTodo() {
		Iterable<Categoria> categorias = cr.findAll();
		for (Categoria cat : categorias) {
			System.out.println(cat);
			
		}
	}
	
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(4);
		ids.add(10);
		Iterable<Categoria> categorias = cr.findAllById(ids);
		for (Categoria cat : categorias) {
			System.out.println(cat);
			
		}
	}
	
	private void eliminarTodo() {
		cr.deleteAll();
	}
	
	private void conteo() {
		long count = cr.count();
		System.out.println("Total categorias: " + count);
	}
	
	private void eliminar() {
		int idCategoria = 1;
		cr.deleteById(idCategoria);
	}
	
	private void modificar() {
		Optional<Categoria> optional = cr.findById(2);
		if(optional.isPresent()) {
			Categoria catTem = optional.get();
			catTem.setNombre("Electronica");
			cr.save(catTem);
			System.out.println(optional.get());
		}else 
			System.out.println("Categoria no encontrada");
	}
	
	private void buscarPorId() {
		Optional<Categoria> optional = cr.findById(1);
		if(optional.isPresent())
			System.out.println(optional.get());
		else 
			System.out.println("Categoria no encontrada");
	}
	
	private void guardar() {
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con contabilidad");
		cr.save(cat);
		System.out.println(cat);
	}
	
		

}
