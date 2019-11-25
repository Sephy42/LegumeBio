package com.formation.persistence.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.formation.persistence.entities.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{

}
