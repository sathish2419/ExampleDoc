package com.spring.spring.Srevice;

import com.spring.spring.Entity.Animals;
import com.spring.spring.Repository.AnimalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    @Autowired
    private AnimalsRepository repository;
    public Animals saveanimals(Animals animals){
        return repository.save(animals);
    }
    public List<Animals> getanimals(){
        return repository.findAll();
    }
    public  Animals getAnimalsById(int id){
        return repository.findById(id).orElse(null);
    }
    public Animals getAnimalsByName(String name){
        return repository.findByName(name);
    }
    public Animals updateAnimals (Animals animals){
        Animals existingAnimals = repository.findById(animals.getId()).orElse(null);
        existingAnimals.setName(animals.getName());
        existingAnimals.setCharacter(animals.getCharacter());
        existingAnimals.setSound(animals.getSound());
        return repository.save(existingAnimals);
    }
}

