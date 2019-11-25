package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.PanierCommande;

public interface IPanierCommandeRepository extends JpaRepository<PanierCommande, Long>{

}
