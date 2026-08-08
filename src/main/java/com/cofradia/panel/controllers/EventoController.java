package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Evento;
import com.cofradia.panel.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    /**
     * Endpoint to get all the events in the database.
     * @return All events of the forum.
     */
    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    /**
     * Endpoint to create and save a new event in the database.
     * @param newEvento The JSON payload automatically mapped to an Evento object.
     * @return The saved Evento object, including the auto-generated ID.
     */
    @PostMapping
    public Evento createEvento(@RequestBody Evento newEvento) {
        return eventoRepository.save(newEvento);
    }

    /**
     * Endpoint to delete an event by its ID.
     * @param id The unique identifier of the event to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        eventoRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing event.
     * @param id The ID of the event to update (from the URL).
     * @param eventoActualizado The new data from the frontend form.
     * @return The updated and saved event.
     */
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody Evento eventoActualizado) {
        return eventoRepository.findById(id)
                .map(eventoExistente -> {
                    eventoExistente.setTitulo(eventoActualizado.getTitulo());
                    eventoExistente.setFecha(eventoActualizado.getFecha());
                    eventoExistente.setDescripcion(eventoActualizado.getDescripcion());
                    eventoExistente.setUbicacion(eventoActualizado.getUbicacion());
                    return eventoRepository.save(eventoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }
}
