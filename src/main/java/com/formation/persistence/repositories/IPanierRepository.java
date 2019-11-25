package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Panier;

public interface IPanierRepository extends JpaRepository<Panier, Long>{

}
