package com.formation.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formation.dto.CategorieFull;
import com.formation.persistence.entities.Categorie;
import com.formation.services.ICategorieService;

@RestController //spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/categorie") //on associe à ce controller un "morceau" d'URL
public class CategorieController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	ICategorieService service;
	
	
	public CategorieController() {
		System.out.println("instanciation du controller de Categorie");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<CategorieFull> findAll(){
		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, CategorieFull.class))
				.collect(Collectors.toList());
		
	}


	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public CategorieFull findOne (@PathVariable(name = "identifiant") Long id) {		
		return mapper.map(service.findById(id).get(), CategorieFull.class);
	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping() //reçoit un objet categorie en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public CategorieFull save(@RequestBody CategorieFull cat) {
		return mapper.map(service.save(mapper.map(cat, Categorie.class)), CategorieFull.class);
		
	}


}
