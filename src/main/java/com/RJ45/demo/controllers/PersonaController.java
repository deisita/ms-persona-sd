package com.RJ45.demo.controllers;

import com.RJ45.demo.entities.PersonaEntity;
import com.RJ45.demo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class PersonaController{

    @Autowired
    private PersonaRepository personaRepository;


    @GetMapping("/")
    public String getInicio()
    {
        return "Hola!";

    }

    @GetMapping("/personas")
    public List<PersonaEntity> getPersonas(){
        Iterable<PersonaEntity> result = personaRepository.findAll();
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return personasList;
    }

    @GetMapping("persona")
    public PersonaEntity getPersonaId(@PathVariable("id") Integer id) {
        PersonaEntity obj = personaRepository.findById(id).get();
        return obj;
        //return id;
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
    public List<PersonaEntity> getPersonasIdd(@RequestParam int id){
     /** Iterable<PersonaEntity> result = personaRepository.buscaPorNombre(1);

        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        result.forEach(personasList ::add);
        return personasList;*/

         return personaRepository.buscaPorNombre(id);
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
