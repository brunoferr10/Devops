package com.vetlink.vetlink.controller;

import com.vetlink.vetlink.model.Pet;
import com.vetlink.vetlink.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository repository;

    @GetMapping
    public List<Pet> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Pet salvar(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @PutMapping("/{id}")
    public Pet atualizar(@PathVariable Long id,
                         @RequestBody Pet pet) {

        pet.setId(id);

        return repository.save(pet);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        repository.deleteById(id);
    }
}