package com.cofradia.panel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data // Lombok generates getters, setters, and toString.
@NoArgsConstructor // Constructor alone for JPA.
@AllArgsConstructor // Constructor with all arguments.
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArticulo;
    private String categoria;
    private String estadoConservacion;
    private String ubicacion;
}
