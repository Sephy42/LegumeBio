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

//TODO : aucun lien avec les clé étrangeres pour le moment, fils unique
//ameliorer tostring

@Table(name = "produits")
@Entity
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String libelle;
	@Column
	private String description;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_categorie",referencedColumnName = "id")
	private Categorie categorie;
	
	
	public Produit() {
		super();
	}


	public Produit(String libelle, String description, Categorie categorie) {
		super();
		this.id = null;
		this.libelle = libelle;
		this.description = description;
		this.categorie = categorie;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	@Override
	public String toString() {
		return "Produit n°" + id + ", " + libelle + ", => " + description + ", "
				+ categorie;
	}

	
	
	
}
