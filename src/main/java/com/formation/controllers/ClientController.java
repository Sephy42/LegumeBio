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

import com.formation.dto.ClientFull;
import com.formation.dto.ClientLight;
import com.formation.persistence.entities.Client;
import com.formation.services.IClientService;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/client") 		//on associe à ce controller un "morceau" d'URL
public class ClientController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	IClientService service;
	
	
	public ClientController() {
		System.out.println("instanciation du controller de client");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<ClientLight> findAll(){
		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, ClientLight.class))
				.collect(Collectors.toList());
		
	}

	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public ClientFull findOne (@PathVariable(name = "identifiant") Long id) {	
		return mapper.map(service.findById(id).get(), ClientFull.class);

	}
	
	@DeleteMapping(path="/{id}")  
	public void delete (@PathVariable Long id) {
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostMapping() //reçoit un objet client en parametre et le sauve dans le repository pour qu'il le sauve dans la bd
	public ClientFull save(@RequestBody ClientFull cl) {
		return mapper.map(service.save(mapper.map(cl, Client.class)), ClientFull.class);
		
	}
	
	
}
