package com.RJ45.demo.controllers;

import com.RJ45.demo.entities.PersonaEntity;
import com.RJ45.demo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/personas")
public class PersonaController{

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/")
    public List<PersonaEntity> getPersonas(){
        Iterable<PersonaEntity> result = personaRepository.findAll();
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return personasList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaId(@PathVariable("id") Integer id) {
        PersonaEntity obj=null;
        Map<String, Object> response = new HashMap<>();
        try{
            obj = personaRepository.findById(id).orElse(null);
            if (obj==null){
                response.put("mensaje","No se pudo encontrar a la persona.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardaPersona")
    public void agregarPersona(@RequestBody PersonaEntity nueva)
    {
        personaRepository.save(nueva);
    }

    @GetMapping("/search")
    public String search(@RequestParam long id){
        String customer = "";
        customer = personaRepository.findById((int) id).toString();
        return customer;
    }

    @GetMapping("/searchPersonaId")
    public PersonaEntity getPersonasId(@RequestParam int id){
      return personaRepository.findById(id).get();
    }

    @PostMapping("/deletePersona")
    public void deletePersonas(@RequestBody PersonaEntity id)
    {
        personaRepository.delete(id);
    }


    @GetMapping("/p")
    public List<PersonaEntity> getPersonasIdd(@RequestParam String nombre){
     /** Iterable<PersonaEntity> result = personaRepository.buscaPorNombre(1);

        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return personasList;*/

         //return personaRepository.buscaPorNombre(nombre);
        return  personaRepository.findByPrimerNombreAndPrimerApellido("maria", "liberato");
    }

    @GetMapping("/countPersonas")
    public String ContarPersonas()
    {
        return "Total: " + personaRepository.count();
    }

    @GetMapping("/ePersonas")
    public String ExistePersona(@RequestParam int id)
    {
        if (personaRepository.existsById(id)==true)
        {
            return "La persona si existe.";
        }
        else
        {
            return "La persona no existe.";
        }
    }

}
