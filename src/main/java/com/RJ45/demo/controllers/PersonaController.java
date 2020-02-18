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
    public ResponseEntity<?> getPersonas(){

        List<PersonaEntity> obj=null;
        Map<String, Object> response = new HashMap<>();
        try{
            Iterable<PersonaEntity> result = personaRepository.findAll();
            obj = new ArrayList<PersonaEntity>();
            result.forEach(obj ::add);
            if (result==null){
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

    @GetMapping("/{nombre}/{apellido}")
    public ResponseEntity<?> getPersonaNombreApellido(@PathVariable("nombre") String nombre,@PathVariable("apellido")  String apellido){

        PersonaEntity obj=null;
        Map<String, Object> response = new HashMap<>();
        try{
            obj = personaRepository.findByPrimerNombreAndPrimerApellido(nombre, apellido);
            if (obj==null){
                response.put("mensaje","No se encontró la persona.");
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

    @PostMapping("/add")
    public ResponseEntity<?> addPersona(@RequestBody PersonaEntity nueva){

        PersonaEntity obj=null;
        Map<String, Object> response = new HashMap<>();
        try{
            obj = personaRepository.save(nueva);
            if (obj==null){
                response.put("mensaje","No se agregó la persona.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("mensaje","se agregó la persona.");
            response.put("mensaje2",obj);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deletePersona/{id}")
    public ResponseEntity<?> deletePersonas(@PathVariable("id") int id)
    {
        Map<String, Object> response = new HashMap<>();
        try{
            personaRepository.deleteById(id);
            /**Boolean result = personaRepository.existsById(id);
            if (result==true){
                response.put("mensaje","No se logró eliminar la persona.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }*/
            response.put("mensaje","Se eliminnó la persona.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/cPersonas")
    public ResponseEntity<?> cPersonas()
    {
        Map<String, Object> response = new HashMap<>();

        try{
            Long result = personaRepository.count();
            if (result <= 0){
                response.put("mensaje","No hay personas para contar.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("mensaje","Se encontraron " + result + " personas.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<?>  ExistPersona(@PathVariable("id") int id)
    {
        Map<String, Object> response = new HashMap<>();
        try{
            Boolean result = personaRepository.existsById(id);
            if (result==false){
                response.put("mensaje","La persona no existe.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("mensaje","La persona existe.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePersonaId(@PathVariable("id") Integer id, @RequestBody PersonaEntity updatePersona) {
        PersonaEntity obj=null;
        Map<String, Object> response = new HashMap<>();
        try{

            obj = personaRepository.findById(id).orElse(null);
            if (obj==null){
                response.put("mensaje","No se encontro la persona.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            PersonaEntity persona = personaRepository.findById(id).get();
            persona.setPrimerNombre(updatePersona.getPrimerNombre());
            persona.setSegundoNombre(updatePersona.getSegundoNombre());
            persona.setPrimerApellido(updatePersona.getPrimerApellido());
            persona.setSegundoApellido(updatePersona.getSegundoApellido());
            persona.setFechaNacimiento(updatePersona.getFechaNacimiento());
            persona.setGenero(updatePersona.getGenero());
            personaRepository.save(persona);

            response.put("mensaje","se actualizo la persona.");
            response.put("mensaje2",persona);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch(DataAccessException e){
            response.put("mensaje","Error al realizar la consulta en la base de datos.");
            response.put("Error",e.getMessage()+"##"+e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /** @PostMapping("/agregarPersona")
     public void agregarPersona(@RequestBody PersonaEntity nueva)
     {
         personaRepository.save(nueva);
     }*/

    /**@GetMapping("/p")
    public List<PersonaEntity> getPersonasIdd(@RequestParam String nombre){
    return  personaRepository.findByPrimerNombreAndPrimerApellido("maria", "liberato");
    }*/

    /**@GetMapping("/countPersonas")
    public String ContarPersonas()
    {
    return "Total: " + personaRepository.count();
    }*/

    /**@GetMapping("/search")
    public String search(@RequestParam long id){
    String customer = "";
    customer = personaRepository.findById((int) id).toString();
    return customer;
    }*/

    /**@GetMapping("/searchPersonaId")
    public PersonaEntity getPersonasId(@RequestParam int id){
    return personaRepository.findById(id).get();
    }*/

   /** @GetMapping("/ePersonas/{id}")
    public String ExistePersona(@PathVariable("id") int id)
    {
        if (personaRepository.existsById(id)==true)
        {
            return "La persona existe.";
        }
        else
        {
            return "La persona no existe.";
        }
    }*/

   /**@PostMapping("/deletePersona")
   public void deletePersonas(@RequestBody PersonaEntity id)
   {
       personaRepository.delete(id);
   }*/
}
