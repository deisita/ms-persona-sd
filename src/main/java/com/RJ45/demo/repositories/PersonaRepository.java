package com.RJ45.demo.repositories;

import com.RJ45.demo.entities.PersonaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer> {
    //Query(value = "select * from deisy.persona p where p.id = :id", nativeQuery = true)
   // List<PersonaEntity>buscaPorNombre(@Param("nombre") String primer_nombre);


    List<PersonaEntity>findByPrimerNombreAndPrimerApellido(String primerNombre,String primerApellido);
    /**
     * Esta funcion hace exactamente lo mismo que la funcion buscaPorNombre pero utilizando DSL (Domain Specificic Lenguage) de Spring
     * @param nombre Nombre de usuario a buscar (sin wildcards, ya lo pone JPL)
     * @return Lista de Usuarios a buscar
     */
   // List<PersonaEntity> findIsLikeNombreOrderByNombre(Integer id);
   // @Query(value = "SELECT * FROM persona")
    //List<PersonaEntity> findPerson(Integer id);
    //List<PersonaEntity> findPerson(@Param("id") Integer id);
}






