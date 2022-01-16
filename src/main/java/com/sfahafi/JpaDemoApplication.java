package com.sfahafi;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
		buscarTodosPaginacionOrdenados();	
		
	}
	
	// Metodos I JpaRepository
	private void buscarTodosPaginacionOrdenados() {
		Page<Categoria> page = cr.findAll(PageRequest.of(1, 5, Sort.by("nombre").descending())); // of(1, 5) Pagina y cantidad de registros
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total Paginas: " + page.getTotalPages());
		for(Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	private void buscarTodosPaginacion() {
		Page<Categoria> page = cr.findAll(PageRequest.of(1, 5)); // of(1, 5) Pagina y cantidad de registros
		System.out.println("Total registros: " + page.getTotalElements());
		System.out.println("Total Paginas: " + page.getTotalPages());
		for(Categoria c : page.getContent()) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	private void buscarTodosOrdenado() {
		List<Categoria> categorias = cr.findAll(Sort.by("nombre").descending()); // descending() forma descendente, sin eso es ascendente
		for(Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	private void borrarTodoEnBloque() {
		cr.deleteAllInBatch();
	}
	
	private void buscarTodasJpa() {
		List<Categoria> categorias = cr.findAll();
		for(Categoria c : categorias) {
			System.out.println(c.getId() + " " + c.getNombre());
		}
	}
	
	//*************************************************************************************************
	
	// metodos de I CrudRepository
	private void guardarTodas() {
		 List<Categoria> categorias = getListaCategorias();
		 cr.saveAll(categorias);
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
	
	
	private List<Categoria> getListaCategorias(){
		List<Categoria> lista = new LinkedList<Categoria>();
		
		Categoria cat1 = new Categoria();
		cat1.setNombre("Backend");
		cat1.setDescripcion("Desarrollo de software Back");
		
		Categoria cat2 = new Categoria();
		cat2.setNombre("Frontend");
		cat2.setDescripcion("Desarrollo de software Front");
		
		Categoria cat3 = new Categoria();
		cat3.setNombre("Fullstack");
		cat3.setDescripcion("Desarrollo de software Full");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		
		return lista;
		
	}
		

}
