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

import com.formation.dto.CommandeFull;
import com.formation.dto.CommandeLight;
import com.formation.dto.PanierCommandeLight;
import com.formation.persistence.entities.Commande;
import com.formation.persistence.entities.PanierCommande;
import com.formation.services.ICommandeService;

@RestController 						//spring fait de cette class un controler rest =>il en fait un singleton, on a pas à faire d'instance, spring le fait auto
@RequestMapping(path = "/commande") 		//on associe à ce controller un "morceau" d'URL
public class CommandeController {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	ICommandeService service;
	
	
	public CommandeController() {
		System.out.println("instanciation du controller de commande");
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)  //associe un chemin à une méthode (en get)
	public List<CommandeLight> findAll(){
		return service.findAll()
				.stream()
				.map(c -> mapper.map(c, CommandeLight.class))
				.collect(Collectors.toList());
		
	}

	@GetMapping(path="/{identifiant}")  // version cool de @RequestMapping(path="/{identifiant}", method = RequestMethod.GET) 
	public CommandeFull findOne (@PathVariable(name = "identifiant") Long id) {	
		
		CommandeFull commande = mapper.map(service.findById(id).get(), CommandeFull.class);
		
		Set<PanierCommande> panierCom = service.findById(id).get().getPaniercommande();
		
	
		Set<PanierCommandeLight> panLight = new HashSet<>() ;
		
		panierCom.stream().forEach(pc -> {
			
			
			PanierCommandeLight pcl = mapper.map(pc.getPanier(), PanierCommandeLight.class);
			
			pcl.setQuantite(pc.getNbPanierDansCommande());
			
			panLight.add(pcl);
			
		});

		commande.setPaniers(panLight);
		
		return commande;
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
	public CommandeFull save(@RequestBody CommandeFull com) {
		return mapper.map(service.save(mapper.map(com, Commande.class)), CommandeFull.class);
		
	}
	
	
}
