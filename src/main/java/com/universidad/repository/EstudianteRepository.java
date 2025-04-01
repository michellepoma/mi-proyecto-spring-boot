package com.universidad.repository;

import com.universidad.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // JpaRepository ya tiene implementados los métodos findById y existsById
}
