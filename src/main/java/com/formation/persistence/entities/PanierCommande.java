package com.formation.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Table(name = "paniers_to_commandes")
@Entity
public class PanierCommande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (name = "quantite")
	private Integer nbPanierDansCommande;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_panier",referencedColumnName = "id")
	private Panier panier;
	
	
	public PanierCommande() {
		super();
	}


	public PanierCommande(Integer nbPanierDansCommande, Panier panier) {
		super();
		this.id = null;
		this.nbPanierDansCommande = nbPanierDansCommande;
		this.panier = panier;
	}
	
	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getNbPanierDansCommande() {
		return nbPanierDansCommande;
	}


	public void setNbPanierDansCommande(Integer nbPanierDansCommande) {
		this.nbPanierDansCommande = nbPanierDansCommande;
	}


	public Panier getPanier() {
		return panier;
	}


	public void setPanier(Panier panier) {
		this.panier = panier;
	}


	@Override
	public String toString() {
		return "PanierCommande numero " + id + ", " + nbPanierDansCommande + " paniers," + panier;
	}


	
	
	
	

}
