package com.cofradia.panel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
@Data // Lombok generates getters, setters, and toString.
@NoArgsConstructor // Constructor alone for JPA.
@AllArgsConstructor // Constructor with all arguments.
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private LocalDate fecha;
    private String descripcion;
    private String ubicacion;
}
