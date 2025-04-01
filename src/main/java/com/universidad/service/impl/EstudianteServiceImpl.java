package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        
        return estudiantes.stream()
            .map(estudiante -> new EstudianteDTO(
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getApellido(),
                    estudiante.getEmail(),
                    estudiante.getFechaNacimiento(),
                    estudiante.getNumeroInscripcion()
            ))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<EstudianteDTO> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id)
                .map(estudiante -> new EstudianteDTO(
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getApellido(),
                        estudiante.getEmail(),
                        estudiante.getFechaNacimiento(),
                        estudiante.getNumeroInscripcion()
                ));
    }

    @Override
    public EstudianteDTO guardarEstudiante(Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteRepository.save(estudiante);
        return new EstudianteDTO(
                nuevoEstudiante.getId(),
                nuevoEstudiante.getNombre(),
                nuevoEstudiante.getApellido(),
                nuevoEstudiante.getEmail(),
                nuevoEstudiante.getFechaNacimiento(),
                nuevoEstudiante.getNumeroInscripcion()
        );
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(est -> {
                    
                    est.setNombre(estudianteActualizado.getNombre());
                    est.setApellido(estudianteActualizado.getApellido());
                    est.setEmail(estudianteActualizado.getEmail());
                    est.setFechaNacimiento(estudianteActualizado.getFechaNacimiento());
                    est.setNumeroInscripcion(estudianteActualizado.getNumeroInscripcion());

                    Estudiante estudianteGuardado = estudianteRepository.save(est);
                    return new EstudianteDTO(
                            estudianteGuardado.getId(),
                            estudianteGuardado.getNombre(),
                            estudianteGuardado.getApellido(),
                            estudianteGuardado.getEmail(),
                            estudianteGuardado.getFechaNacimiento(),
                            estudianteGuardado.getNumeroInscripcion()
                    );
                }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }


    @Override
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        estudianteRepository.deleteById(id);
    }
}
