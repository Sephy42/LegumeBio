package com.formation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.formation.exceptions.NotFoundException;
import com.formation.persistence.entities.ProduitDuPanier;
import com.formation.persistence.repositories.IProduitDuPanierRepository;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/produitdupanier") 		//on associe à ce controller un "morceau" d'URL
public class ProduitDuPanierController {
	
	@Autowired
	IProduitDuPanierRepository repo;
	
	
	public ProduitDuPanierController() {
		System.out.println("instanciation du controller de produit du panier");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<ProduitDuPanier> findAll(){
		return repo.findAll();
		
	}

	
	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public ProduitDuPanier findOne (@PathVariable(name = "identifiant") Long id) {		
		Optional<ProduitDuPanier> opt = repo.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("La liaison produit du panier "+id+" n'a pas été trouvée");
		}
		return opt.get();
	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		ProduitDuPanier cl = findOne(id);
		try {
			repo.delete(cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping(path="/{}") //reçoit un objet commande en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public ProduitDuPanier save(@RequestBody ProduitDuPanier cl) {
		return repo.save(cl);
		
	}
	
	
}
