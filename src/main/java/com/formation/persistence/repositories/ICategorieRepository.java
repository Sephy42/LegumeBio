package com.formation.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.persistence.entities.Categorie;


public interface ICategorieRepository extends JpaRepository<Categorie, Long>{

}
