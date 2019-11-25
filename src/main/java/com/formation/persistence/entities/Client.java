package com.formation.persistence.entities;
import java.util.Date;
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


@Table(name = "clients")
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column (name = "date_naissance")
	private Date naissance;
	@Column (name = "email")
	private String mail;
	@Column (name = "telephone")
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="id_client",referencedColumnName = "id")
	private Set<Commande> commandes;
	
	

	
	public Client() {
		this.commandes = new TreeSet<>();
	}
	
	

	public Client(String nom, String prenom, Date naissance, String mail, String phone) {
		super();
		this.id = null;
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
		this.mail = mail;
		this.phone = phone;
		this.commandes = new TreeSet<>();
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getNaissance() {
		return naissance;
	}


	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	public Set<Commande> getCommandes() {
		return commandes;
	}


	/* add a Commande to the attribute commandes */
	public void addCommandes(Commande commande) {
		this.commandes.add(commande);
	}

	/* delete a Commande from the attribute commandes */
	public boolean deleteCommandes(Commande commande) {
		return this.commandes.remove(commande);
	}
	
	/* Clear the attribute commandes */
	public void clearCommandes() {
		this.commandes.clear();
	}



	@Override	
	public String toString() {
		if (commandes.isEmpty()) {
			return "client "+id + ", " + nom + ", " + prenom + ", " + naissance + ", "
					+ mail + ", " + phone;
		}
		
		String str = "client " +id + ", " + nom + ", " + prenom + ", " + naissance + ", "
				+ mail + ", " + phone + ", commandes :\n";
		for (Commande c : commandes) {
			str += "\t"+c +" \n";
		}
		
		return str;
	}



	


	
	
	
	
	
}
