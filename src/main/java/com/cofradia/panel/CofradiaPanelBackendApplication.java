package com.cofradia.panel;

import com.cofradia.panel.models.Hermano;
import com.cofradia.panel.repositories.HermanoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CofradiaPanelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CofradiaPanelBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(HermanoRepository repository) {
        return args -> {
            repository.saveAll(List.of(
                    new Hermano(null, "Antonio", "Pérez García", LocalDate.of(2015, 3, 12), true),
                    new Hermano(null, "María", "Gómez López", LocalDate.of(2018, 5, 24), false),
                    new Hermano(null, "Carlos", "Ruiz Navarro", LocalDate.of(2020, 1, 10), true),
                    new Hermano(null, "Laura", "Martínez Silva", LocalDate.of(2022, 11, 5), true),
                    new Hermano(null, "Manuel", "Díaz", LocalDate.of(2023, 2, 15), false)
            ));
        };
    }
}