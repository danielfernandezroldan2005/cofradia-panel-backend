package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Auditoria;
import com.cofradia.panel.repositories.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auditorias")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class AuditoriaController {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    /**
     * Endpoint to get all the audit logs in the database.
     * @return All audit logs.
     */
    @GetMapping
    public List<Auditoria> getAllAuditorias() {
        return auditoriaRepository.findAll();
    }

    /**
     * Endpoint to create and save a new audit log in the database.
     * @param newAuditoria The JSON payload automatically mapped to an Auditoria object.
     * @return The saved Auditoria object, including the auto-generated ID.
     */
    @PostMapping
    public Auditoria createAuditoria(@RequestBody Auditoria newAuditoria) {
        return auditoriaRepository.save(newAuditoria);
    }

    /**
     * Endpoint to delete an audit log by its ID.
     * @param id The unique identifier of the audit log to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteAuditoria(@PathVariable Long id) {
        auditoriaRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing audit log.
     * @param id The ID of the audit log to update (from the URL).
     * @param auditoriaActualizada The new data from the frontend form.
     * @return The updated and saved audit log.
     */
    @PutMapping("/{id}")
    public Auditoria updateAuditoria(@PathVariable Long id, @RequestBody Auditoria auditoriaActualizada) {
        return auditoriaRepository.findById(id)
                .map(auditoriaExistente -> {
                    auditoriaExistente.setEntidadAfectada(auditoriaActualizada.getEntidadAfectada());
                    auditoriaExistente.setOperacion(auditoriaActualizada.getOperacion());
                    auditoriaExistente.setFechaOperacion(auditoriaActualizada.getFechaOperacion());
                    auditoriaExistente.setUsuario(auditoriaActualizada.getUsuario());
                    return auditoriaRepository.save(auditoriaExistente);
                })
                .orElseThrow(() -> new RuntimeException("Audit log not found with id: " + id));
    }
}
