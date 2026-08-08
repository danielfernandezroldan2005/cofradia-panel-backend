package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Biografia;
import com.cofradia.panel.repositories.BiografiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/biografias")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class BiografiaController {

    @Autowired
    private BiografiaRepository biografiaRepository;

    /**
     * Endpoint to get all the historical milestones in the database.
     * @return All biography milestones.
     */
    @GetMapping
    public List<Biografia> getAllBiografias() {
        return biografiaRepository.findAll();
    }

    /**
     * Endpoint to create and save a new biography milestone in the database.
     * @param newBiografia The JSON payload automatically mapped to a Biografia object.
     * @return The saved Biografia object, including the auto-generated ID.
     */
    @PostMapping
    public Biografia createBiografia(@RequestBody Biografia newBiografia) {
        return biografiaRepository.save(newBiografia);
    }

    /**
     * Endpoint to delete a biography milestone by its ID.
     * @param id The unique identifier of the milestone to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteBiografia(@PathVariable Long id) {
        biografiaRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing biography milestone.
     * @param id The ID of the milestone to update (from the URL).
     * @param biografiaActualizada The new data from the frontend form.
     * @return The updated and saved milestone.
     */
    @PutMapping("/{id}")
    public Biografia updateBiografia(@PathVariable Long id, @RequestBody Biografia biografiaActualizada) {
        return biografiaRepository.findById(id)
                .map(biografiaExistente -> {
                    biografiaExistente.setTitulo(biografiaActualizada.getTitulo());
                    biografiaExistente.setContenido(biografiaActualizada.getContenido());
                    biografiaExistente.setFechaHito(biografiaActualizada.getFechaHito());
                    biografiaExistente.setImagenUrl(biografiaActualizada.getImagenUrl());
                    return biografiaRepository.save(biografiaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Milestone not found with id: " + id));
    }
}
