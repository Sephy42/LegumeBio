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
import com.formation.persistence.entities.Client;
import com.formation.persistence.repositories.IClientRepository;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/client") 		//on associe à ce controller un "morceau" d'URL
public class ClientController {
	
	@Autowired
	IClientRepository repo;
	
	
	public ClientController() {
		System.out.println("instanciation du controller de client");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<Client> findAll(){
		return repo.findAll();
		
	}

	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public Client findOne (@PathVariable(name = "identifiant") Long id) {		
		Optional<Client> opt = repo.findById(id);
		if (opt.isEmpty()) {
			throw new NotFoundException("Le client "+id+" n'a pas été trouvé");
		}
		return opt.get();
	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		Client cl = findOne(id);
		try {
			repo.delete(cl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping(path="/{}") //reçoit un objet client en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public Client save(@RequestBody Client cl) {
		return repo.save(cl);
		
	}
	
	
}
