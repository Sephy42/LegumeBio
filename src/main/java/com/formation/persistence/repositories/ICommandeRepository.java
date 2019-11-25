package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Commande;

public interface ICommandeRepository extends JpaRepository < Commande, Long>{

}
