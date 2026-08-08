package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Documento;
import com.cofradia.panel.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class DocumentoController {

    @Autowired
    private DocumentoRepository documentoRepository;

    /**
     * Endpoint to get all documents in the database.
     * @return All documents.
     */
    @GetMapping
    public List<Documento> getAllDocumentos() {
        return documentoRepository.findAll();
    }

    /**
     * Endpoint to create and save a new document in the database.
     * @param newDocumento The JSON payload automatically mapped to a Documento object.
     * @return The saved Documento object, including the auto-generated ID.
     */
    @PostMapping
    public Documento createDocumento(@RequestBody Documento newDocumento) {
        return documentoRepository.save(newDocumento);
    }

    /**
     * Endpoint to delete a document by its ID.
     * @param id The unique identifier of the document to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteDocumento(@PathVariable Long id) {
        documentoRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing document.
     * @param id The ID of the document to update (from the URL).
     * @param documentoActualizado The new data from the frontend form.
     * @return The updated and saved document.
     */
    @PutMapping("/{id}")
    public Documento updateDocumento(@PathVariable Long id, @RequestBody Documento documentoActualizado) {
        return documentoRepository.findById(id)
                .map(documentoExistente -> {
                    documentoExistente.setTitulo(documentoActualizado.getTitulo());
                    documentoExistente.setTipo(documentoActualizado.getTipo());
                    documentoExistente.setFechaCreacion(documentoActualizado.getFechaCreacion());
                    documentoExistente.setEnlaceArchivo(documentoActualizado.getEnlaceArchivo());
                    return documentoRepository.save(documentoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
    }
}
