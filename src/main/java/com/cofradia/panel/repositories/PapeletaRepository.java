package com.cofradia.panel.repositories;

import com.cofradia.panel.models.Papeleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PapeletaRepository extends JpaRepository<Papeleta, Long> {
}
