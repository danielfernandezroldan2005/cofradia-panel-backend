package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Inventory;
import com.cofradia.panel.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Endpoint to get all inventory assets in the database.
     * @return All assets in the inventory.
     */
    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    /**
     * Endpoint to create and save a new asset in the inventory.
     * @param newAsset The JSON payload automatically mapped to an Inventory object.
     * @return The saved Inventory object, including the auto-generated ID.
     */
    @PostMapping
    public Inventory createAsset(@RequestBody Inventory newAsset) {
        return inventoryRepository.save(newAsset);
    }

    /**
     * Endpoint to delete an inventory asset by its ID.
     * @param id The unique identifier of the asset to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        inventoryRepository.deleteById(id);
    }

    /**
     * Endpoint to update an existing inventory asset.
     * @param id The ID of the asset to update (from the URL).
     * @param assetActualizado The new data from the frontend form.
     * @return The updated and saved asset.
     */
    @PutMapping("/{id}")
    public Inventory updateAsset(@PathVariable Long id, @RequestBody Inventory assetActualizado) {
        return inventoryRepository.findById(id)
                .map(assetExistente -> {
                    assetExistente.setNombreArticulo(assetActualizado.getNombreArticulo());
                    assetExistente.setCategoria(assetActualizado.getCategoria());
                    assetExistente.setEstadoConservacion(assetActualizado.getEstadoConservacion());
                    assetExistente.setUbicacion(assetActualizado.getUbicacion());
                    return inventoryRepository.save(assetExistente);
                })
                .orElseThrow(() -> new RuntimeException("Asset not found with id: " + id));
    }
}
