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

    @PostMapping("/persona")
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
    public List<PersonaEntity> getPersonasId(@RequestParam int id){
        Optional<PersonaEntity> result = personaRepository.findById(id);
        List<PersonaEntity> personasList = new ArrayList<PersonaEntity>();
        //result.forEach(personasList::add);
        return personasList;
    }

    public interface ICityService {

        Optional<City> findById(Long id);
    }
    public Optional<City> findById(Long id) {

        return cityRepository.findById(id);
    }
    var id1 = 2L;
        cityService.findById(id1).ifPresent(System.out::println);

    var id2 = 24L;
    var val = cityService.findById(id2);

        if (val.isPresent()) {
        System.out.println(val.get());
    } else {
        System.out.printf("No city found with id %d%n", id2);
    }

   // @GetMapping("/searchbyfirstname/{firstname}")
  //  public List<PersonaEntity> fetchDataByFirstName(@RequestBody String firstname){
       // List<PersonaEntity> customers = personaRepository.findByFirstName(firstname);
       // List<PersonaEntity> customerUI = new ArrayList<>();

      //  for (PersonaEntity customer : customers) {
        //    customerUI.add(new CustomerUI(PersonaEntity.primer_nombre,customer.getLastName()));
      //  }
       // return customerUI;
    //}
}
