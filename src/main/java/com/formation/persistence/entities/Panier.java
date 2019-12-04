package com.formation.persistence.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "paniers")
@Entity
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Date debut;
	@Column
	private Date fin;
	@Column 
	private Float prix;
	@Column (name = "quantite_dispo")
	private Integer nb;
	@Column
	private String libelle;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	//@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_panier",referencedColumnName = "id")
	private Set<ProduitDuPanier> produitDuPanier;
	
	
	
	public Panier() {
		super();
		produitDuPanier = new HashSet<>();
	}


	public Panier( Date debut, Date fin, Float prix, Integer nb, String libelle) {
		super();
		this.id = null;
		this.debut = debut;
		this.fin = fin;
		this.prix = prix;
		this.nb = nb;
		this.libelle = libelle;
		this.produitDuPanier = new HashSet<>();
	}
	
	


	public Panier(Date debut, Date fin, Float prix, Integer nb, String libelle,
			Set<ProduitDuPanier> produitDuPanier) {
		super();
		this.id = null;
		this.debut = debut;
		this.fin = fin;
		this.prix = prix;
		this.nb = nb;
		this.libelle = libelle;
		this.produitDuPanier = produitDuPanier;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDebut() {
		return debut;
	}


	public void setDebut(Date debut) {
		this.debut = debut;
	}


	public Date getFin() {
		return fin;
	}


	public void setFin(Date fin) {
		this.fin = fin;
	}


	public Float getPrix() {
		return prix;
	}


	public void setPrix(Float prix) {
		this.prix = prix;
	}


	public Integer getNb() {
		return nb;
	}


	public void setNb(Integer nb) {
		this.nb = nb;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	

	public Set<ProduitDuPanier> getProduitDuPanier() {
		return produitDuPanier;
	}


	public void setProduitDuPanier(Set<ProduitDuPanier> produitDuPanier) {
		this.produitDuPanier = produitDuPanier;
	}


	@Override
	public String toString() {
		if (produitDuPanier==null) {
			return "panier n°"+ id + ", dispo de " + debut + " à " + fin + " en " + nb + " exemplaires à "+prix+" €, contenant : aucun produit, et c'est pas normal du tout => "
					+ libelle ;
		}
		
		String str = "panier n°"+ id + " \""+libelle+"\" dispo de " + debut + " à " + fin + " en " + nb + " exemplaires à "+prix+" €, contenant : \n";
		
		for (ProduitDuPanier pdp : produitDuPanier) {
			str += "\t\t\t"+pdp.getProduit()+"\n";
		}
		return str;
	}
	
	
	
	
}
