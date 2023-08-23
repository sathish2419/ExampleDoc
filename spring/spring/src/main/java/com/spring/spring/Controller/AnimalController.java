package com.spring.spring.Controller;

import com.spring.spring.Entity.Animals;
import com.spring.spring.Srevice.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnimalController {
    @Autowired
    private AnimalService service;
    @PostMapping("/saveAnimals")
    public Animals addAnimals (@RequestBody Animals animals){
        return service.saveanimals(animals);
    }
    @GetMapping("/list")
    public List<Animals> findAll (){
        return service.getanimals();
    }
    @GetMapping("/id/{id}")
    public Animals findById(@PathVariable int id){
        return service.getAnimalsById(id);
    }
    @GetMapping("/name/{name}")
    public Animals findByName (@PathVariable String name){
        return service.getAnimalsByName(name);
    }
    @PutMapping("/update/{id}")
    public Animals updateAnimals(@RequestBody Animals forest){
        return service.updateAnimals(forest);
    }

}


