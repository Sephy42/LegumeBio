package com.formation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.formation.persistence.entities.Client;
import com.formation.persistence.repositories.IClientRepository;
import com.formation.persistence.repositories.ICommandeRepository;


@SpringBootApplication
public class MonApplication implements CommandLineRunner {

	// au demarrage de l'appli je sais pas quoi
	// spring injecte un singleton qui va bien dans clientRepo
	@Autowired
	private IClientRepository clientRepo;
	
	@Autowired
	private ICommandeRepository commandeRepo;
	
	
	
	
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MonApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		/**************************/
		/***** Début du run  ******/
		/**************************/
		System.out.println("\n\n********************\n    Debut du run   \n********************\n");
		
		
		Client client = new Client( "Mussal","Pierrot", new Date(), "truc@uazefu","2646848");
		//clientRepo.findAll().stream().forEach(c -> System.out.println(c));
		client.setId(11L);
	
		
		clientRepo.save(client);
		
		
		/**************************/
		/* affichage de la table  */
		/**************************/
		System.out.println("Contenu de la table Client dans la BD");
		clientRepo.findAll().stream().forEach(c -> System.out.println(c));
		//commandeRepo.findAll().stream().forEach(c -> System.out.println(c));
		
		
		
		/**************************/
		/* affichage des objects  */
		/**************************/
/*		System.out.println("\n\nContenu de la liste de client");
		List<Client> clientlist = clientRepo.findAll();
		
		afficherList(clientlist);
*/		
		
		
		
		
		/**************************/
		/***** fin du run  ******/
		/**************************/
		System.out.println("\n\n********************\n     Fin du run    \n********************\n");
		
	}

	
	
	/* fonction d'affichage moins dégueu pour une list */
	
	public void afficherList( List<?> l) {
		for (Object o : l) {
			System.out.println(o);
		}
	}
	
}