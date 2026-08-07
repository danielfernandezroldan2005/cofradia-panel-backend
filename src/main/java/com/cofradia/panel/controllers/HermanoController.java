package com.cofradia.panel.controllers;

import com.cofradia.panel.models.Hermano;
import com.cofradia.panel.repositories.HermanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hermanos")
@CrossOrigin(origins = "http://localhost:4200") // For avoiding CORS errors from Angular.
public class HermanoController {

    @Autowired
    private HermanoRepository hermanoRepository;

    @GetMapping
    public List<Hermano> getAllHermanos() {
        return hermanoRepository.findAll();
    }
}