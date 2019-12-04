package com.formation.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

import com.formation.dto.PanierFull;
import com.formation.dto.PanierLight;
import com.formation.dto.ProduitLight;
import com.formation.persistence.entities.Panier;
import com.formation.persistence.entities.ProduitDuPanier;
import com.formation.services.IPanierService;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/panier") 		//on associe à ce controller un "morceau" d'URL
public class PanierController {
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	IPanierService service;
	
	
	
	
	public PanierController() {
		System.out.println("instanciation du controller de panier");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<PanierLight> findAll(){
		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, PanierLight.class))
				.collect(Collectors.toList());
		
	}

	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public PanierFull findOne (@PathVariable(name = "identifiant") Long id) {		
		PanierFull panier = mapper.map(service.findById(id).get(), PanierFull.class);
		
		Set<ProduitDuPanier> prodDuPan = service.findById(id).get().getProduitDuPanier();
		
	
		Set<ProduitLight> prodLight = new HashSet<ProduitLight>() ;
		
		prodDuPan.stream().forEach(pdp -> prodLight.add(mapper.map(pdp.getProduit(), ProduitLight.class)));

		panier.setProduits(prodLight);
		
		
		return panier;
	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping() //reçoit un objet commande en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public PanierFull save(@RequestBody PanierFull pan) {
		return mapper.map(service.save(mapper.map(pan, Panier.class)), PanierFull.class);
		
	}
	
	
}
