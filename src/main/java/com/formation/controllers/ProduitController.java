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

import com.formation.dto.ProduitFull;
import com.formation.dto.ProduitLight;
import com.formation.persistence.entities.Produit;
import com.formation.services.IProduitService;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/produit") 		//on associe à ce controller un "morceau" d'URL
public class ProduitController {
	

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	IProduitService service;
	
	
	public ProduitController() {
		System.out.println("instanciation du controller de produit");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<ProduitLight> findAll(){
		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, ProduitLight.class))
				.collect(Collectors.toList());
	}

	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public ProduitFull findOne (@PathVariable(name = "identifiant") Long id) {		
		return mapper.map(service.findById(id).get(), ProduitFull.class);
	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {;
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping() //reçoit un objet commande en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public ProduitFull save(@RequestBody ProduitFull prod) {
		return mapper.map(service.save(mapper.map(prod, Produit.class)), ProduitFull.class);
		
	}
	
	
}
