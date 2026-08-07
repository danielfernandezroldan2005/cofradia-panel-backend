package com.cofradia.panel.repositories;

import com.cofradia.panel.models.Hermano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HermanoRepository extends JpaRepository<Hermano, Long> {
}
