package com.universidad.service;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    Optional<EstudianteDTO> obtenerEstudiantePorId(Long id);
    EstudianteDTO guardarEstudiante(Estudiante estudiante);
    EstudianteDTO actualizarEstudiante(Long id, Estudiante estudianteActualizado);
    void eliminarEstudiante(Long id);
}

