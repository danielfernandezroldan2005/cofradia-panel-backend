package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Papeleta;
import com.cofradia.panel.repositories.PapeletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/papeletas")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class PapeletaController {

    @Autowired
    private PapeletaRepository papeletaRepository;

    /**
     * Endpoint to get all the procession assignments (papeletas) in the database.
     * @return All procession assignments.
     */
    @GetMapping
    public List<Papeleta> getAllPapeletas() {
        return papeletaRepository.findAll();
    }

    /**
     * Endpoint to create and save a new assignment in the database.
     * @param newPapeleta The JSON payload automatically mapped to a Papeleta object.
     * @return The saved Papeleta object, including the auto-generated ID.
     */
    @PostMapping
    public Papeleta createPapeleta(@RequestBody Papeleta newPapeleta) {
        return papeletaRepository.save(newPapeleta);
    }

    /**
     * Endpoint to delete a procession assignment by its ID.
     * @param id The unique identifier of the assignment to delete.
     */
    @DeleteMapping("/{id}")
    public void deletePapeleta(@PathVariable Long id) {
        papeletaRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing procession assignment.
     * @param id The ID of the assignment to update (from the URL).
     * @param papeletaActualizada The new data from the frontend form.
     * @return The updated and saved assignment.
     */
    @PutMapping("/{id}")
    public Papeleta updatePapeleta(@PathVariable Long id, @RequestBody Papeleta papeletaActualizada) {
        return papeletaRepository.findById(id)
                .map(papeletaExistente -> {
                    papeletaExistente.setIdHermano(papeletaActualizada.getIdHermano());
                    papeletaExistente.setPuesto(papeletaActualizada.getPuesto());
                    papeletaExistente.setAnio(papeletaActualizada.getAnio());
                    papeletaExistente.setEstado(papeletaActualizada.getEstado());
                    return papeletaRepository.save(papeletaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Assignment not found with id: " + id));
    }
}
