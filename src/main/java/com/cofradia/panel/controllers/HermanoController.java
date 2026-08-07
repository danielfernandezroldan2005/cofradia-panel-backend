package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Hermano;
import com.cofradia.panel.repositories.HermanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hermanos")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class HermanoController {

    @Autowired
    private HermanoRepository hermanoRepository;

    /**
     * Endpoint to get all the members in the database.
     * @return All members of the brotherhood or cofradia.
     */
    @GetMapping
    public List<Hermano> getAllHermanos() {
        return hermanoRepository.findAll();
    }

    /**
     * Endpoint to create and save a new member (hermano) in the database.
     * @param newHermano The JSON payload automatically mapped to a Hermano object.
     * @return The saved Hermano object, including the auto-generated ID.
     */
    @PostMapping
    public Hermano createHermano(@RequestBody Hermano newHermano) {
        // The repository's save() method automatically persists the entity
        return hermanoRepository.save(newHermano);
    }
}