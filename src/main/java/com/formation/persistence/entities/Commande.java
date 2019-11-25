package com.formation.persistence.entities;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name = "commandes")
@Entity
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Date date;
	@Column ( name = "montant_ht")
	private Double montant;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cmd",referencedColumnName = "id")
	private Set<PanierCommande> panierCommande;
	
	
	
	
	
	public Commande() {
		super();
		panierCommande = new TreeSet<>();
	}
	
	
	public Commande(Date date, Double montant, Set<PanierCommande> paniercommande) {
		super();
		this.id = null;
		this.date = date;
		this.montant = montant;
		this.panierCommande = paniercommande;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	
	
	
	public Set<PanierCommande> getPaniercommande() {
		return panierCommande;
	}


	public void setPaniercommande(Set<PanierCommande> paniercommande) {
		this.panierCommande = paniercommande;
	}


	@Override
	public String toString() {
		
		if (panierCommande == null) {
			return "Commande n°" + id + ", faite le " + date + ", pour " + montant + " €, qui bizzarement n'a pas de panier associé....";
		}
		
		String str = "Commande n°" + id + ", faite le " + date + ", pour " + montant + " €. Liste des paniers : \n";
		
		for (PanierCommande p : panierCommande) {
			str += "\t\t"+p.getNbPanierDansCommande()+" paniers de "+p.getPanier()+"\n";
		}
		
		return str;
	}


	
	
	
	
	

}
