package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.ProduitDuPanier;

public interface IProduitDuPanierRepository extends JpaRepository<ProduitDuPanier, Long>{

}
