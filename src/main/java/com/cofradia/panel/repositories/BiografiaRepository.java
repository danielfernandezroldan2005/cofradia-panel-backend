package com.cofradia.panel.repositories;

import com.cofradia.panel.models.Biografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiografiaRepository extends JpaRepository<Biografia, Long> {
}
