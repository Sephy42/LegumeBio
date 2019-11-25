package com.formation.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "produits_to_paniers")
@Entity
public class ProduitDuPanier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_produit",referencedColumnName = "id")
	private Produit produit;
	
	
	public ProduitDuPanier() {
		super();
	}


	public ProduitDuPanier(Produit produit) {
		super();
		this.id = null;
		this.produit = produit;
	}


	public Long getId() {
		return id;
	}



	public Produit getProduit() {
		return produit;
	}


	public void setProduit(Produit produit) {
		this.produit = produit;
	}


	@Override
	public String toString() {
		return "ProduitDuPanier [id=" + id + ", produit=" + produit + "]";
	}

	
	

}
