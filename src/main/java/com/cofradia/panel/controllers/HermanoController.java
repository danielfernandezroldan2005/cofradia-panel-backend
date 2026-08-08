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

    /**
     * Endpoint to delete a member by their ID.
     * @param id The unique identifier of the member to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteHermano(@PathVariable Long id) {
        hermanoRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing member.
     * @param id The ID of the member to update (from the URL).
     * @param hermanoActualizado The new data from the frontend form.
     * @return The updated and saved member.
     */
    @PutMapping("/{id}")
    public Hermano updateHermano(@PathVariable Long id, @RequestBody Hermano hermanoActualizado) {
        return hermanoRepository.findById(id)
                .map(hermanoExistente -> {
                    hermanoExistente.setNombre(hermanoActualizado.getNombre());
                    hermanoExistente.setApellidos(hermanoActualizado.getApellidos());
                    hermanoExistente.setFechaAlta(hermanoActualizado.getFechaAlta());
                    hermanoExistente.setCuotaAlDia(hermanoActualizado.isCuotaAlDia());
                    return hermanoRepository.save(hermanoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }
}