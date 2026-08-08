package com.cofradia.panel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "biografias")
@Data // Lombok generates getters, setters, and toString.
@NoArgsConstructor // Constructor alone for JPA.
@AllArgsConstructor // Constructor with all arguments.
public class Biografia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 2000) // Support longer text for the biography milestone details.
    private String contenido;

    private LocalDate fechaHito;
    private String imagenUrl;
}
