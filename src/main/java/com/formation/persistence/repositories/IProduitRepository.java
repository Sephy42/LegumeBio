package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long>{

}
