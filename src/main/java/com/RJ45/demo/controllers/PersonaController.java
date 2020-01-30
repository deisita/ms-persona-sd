package com.RJ45.demo.controllers;

import com.RJ45.demo.entities.PersonaEntity;
import com.RJ45.demo.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonaController{

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/")
    public String getPersonas()
    {
        return "Hola!";
    }

    @GetMapping("/personas")
    public List<PersonaEntity> getEmployees(){
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



    @PostMapping("/personas")
    public void agregarPersona(@RequestBody PersonaEntity nueva)
    {
        personaRepository.save(nueva);
    }
}
