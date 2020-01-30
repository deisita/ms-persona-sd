package com.RJ45.demo.repositories;

import com.RJ45.demo.entities.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer> {

   // List<PersonaEntity> findByFirstName(String FirstName);
    //List<PersonaEntity> findById(int id);
   // List<PersonaEntity> findAll ();
}
