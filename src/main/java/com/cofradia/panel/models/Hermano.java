package com.cofradia.panel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "hermanos")
@Data // Lombok generate getters, setters y toString.
@NoArgsConstructor // Constructor alone for JPA.
@AllArgsConstructor // Constructor with all arguments.
public class Hermano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private LocalDate fechaAlta;
    private boolean cuotaAlDia;
}
