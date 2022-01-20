package com.sfahafi;

import java.util.Date;
//import java.util.Iterator;
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
import com.sfahafi.model.Perfil;
import com.sfahafi.model.Usuario;
import com.sfahafi.model.Vacante;
import com.sfahafi.repository.I_CategoriasRepository;
import com.sfahafi.repository.I_PerfilesRepository;
import com.sfahafi.repository.I_UsuariosRepository;
import com.sfahafi.repository.I_VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
	
	@Autowired
	private I_CategoriasRepository cr;
	
	@Autowired
	private I_VacantesRepository vr;
	
	@Autowired
	private I_PerfilesRepository pr;
	
	@Autowired
	private I_UsuariosRepository ur;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {  // psvm seria como la clase principal ejecutable en una aplicacion de escritorio
		crearUsuarioConDosPerfil();	
		
	}
	
	private void crearUsuarioConDosPerfil() {
		Usuario user = new Usuario();
		user.setNombre("Sharbel Fahafi");
		user.setEmail("safahafi18@gmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("sfahafi");
		user.setPassword("3570");
		user.setEstatus(1);
		
		Perfil per1 = new Perfil();
		per1.setId(1);
		
		Perfil per2 = new Perfil();
		per1.setId(2);
		
		user.agregar(per1);
		user.agregar(per2);
		
		ur.save(user);
		
	}
	
	/*
	//****************************************************************************
	// Metodo para agregar pefiles a la base de datos
	
	private void crearPerfilesAplicacion() {
		pr.saveAll(getPerfilesAplicacion());
	}
	
	
	//*****************************************************************************
	
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		vacante.setNombre("profesor de Matematicas");
		vacante.setDescripcion("Se solicita profesor con experiencia");
		vacante.setFecha(new Date());
		vacante.setSalario(546456456.9);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("Escuela.png");
		vacante.setDetalles("dfgdgdfgdgfdfgdfgsdgfsdgfd");
		Categoria cat = new Categoria();
		cat.setId(15);
		vacante.setCategoria(cat);
		vr.save(vacante);
	}
	
	private void buscarVacantes() {
		List<Vacante> lista = vr.findAll();
		for(Vacante v : lista) {
			System.out.println(v.getId() + " " + v.getNombre() + " - " + v.getCategoria().getNombre());
		}
	}
	
	
	//*********************************************************************************************************
	
	// Categorias
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
	
	
	
	private List<Perfil> getPerfilesAplicacion(){
		List<Perfil> lista = new LinkedList<Perfil>();
		
		Perfil per1 = new Perfil();
		per1.setPerfil("SUPERVISOR");
		
		Perfil per2 = new Perfil();
		per2.setPerfil("ADMINISTRADOR");
		
		Perfil per3 = new Perfil();
		per3.setPerfil("USUARIO");
		
		lista.add(per1);
		lista.add(per2);
		lista.add(per3);
		
		return lista;
		
	}
	
	*/
		

}
