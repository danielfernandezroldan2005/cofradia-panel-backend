package com.cofradia.panel.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "cuotas")
@Data // Lombok generates getters, setters, and toString.
@NoArgsConstructor // Constructor alone for JPA.
@AllArgsConstructor // Constructor with all arguments.
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idHermano;
    private Double importe;
    private LocalDate fechaPago;
    private String estado;
}
