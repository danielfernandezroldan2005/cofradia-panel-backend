package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Cuota;
import com.cofradia.panel.repositories.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cuotas")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class CuotaController {

    @Autowired
    private CuotaRepository cuotaRepository;

    /**
     * Endpoint to get all the member fees in the database.
     * @return All fees of the brotherhood.
     */
    @GetMapping
    public List<Cuota> getAllCuotas() {
        return cuotaRepository.findAll();
    }

    /**
     * Endpoint to create and save a new fee in the database.
     * @param newCuota The JSON payload automatically mapped to a Cuota object.
     * @return The saved Cuota object, including the auto-generated ID.
     */
    @PostMapping
    public Cuota createCuota(@RequestBody Cuota newCuota) {
        return cuotaRepository.save(newCuota);
    }

    /**
     * Endpoint to delete a fee by its ID.
     * @param id The unique identifier of the fee to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteCuota(@PathVariable Long id) {
        cuotaRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing fee.
     * @param id The ID of the fee to update (from the URL).
     * @param cuotaActualizada The new data from the frontend form.
     * @return The updated and saved fee.
     */
    @PutMapping("/{id}")
    public Cuota updateCuota(@PathVariable Long id, @RequestBody Cuota cuotaActualizada) {
        return cuotaRepository.findById(id)
                .map(cuotaExistente -> {
                    cuotaExistente.setIdHermano(cuotaActualizada.getIdHermano());
                    cuotaExistente.setImporte(cuotaActualizada.getImporte());
                    cuotaExistente.setFechaPago(cuotaActualizada.getFechaPago());
                    cuotaExistente.setEstado(cuotaActualizada.getEstado());
                    return cuotaRepository.save(cuotaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Fee not found with id: " + id));
    }
}
