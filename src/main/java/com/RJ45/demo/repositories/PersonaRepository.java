package com.RJ45.demo.repositories;

import com.RJ45.demo.entities.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer> {

}
