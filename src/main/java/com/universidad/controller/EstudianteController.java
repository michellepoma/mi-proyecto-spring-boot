package com.universidad.controller;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Listar todos los estudiantes (GET)
    @GetMapping("/estudiantes")
    public ResponseEntity<List<EstudianteDTO>> listarEstudiantes() {
        List<EstudianteDTO> estudiantesDTO = estudianteService.obtenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantesDTO);
    }

    @GetMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiante(@PathVariable Long id) {
        return estudianteService.obtenerEstudiantePorId(id)
                .map(estudianteDTO -> ResponseEntity.ok(estudianteDTO))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear Estudiante (POST)
    @PostMapping("/estudiantes")
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        // Guardar el estudiante y devolver el EstudianteDTO
        EstudianteDTO nuevoEstudianteDTO = estudianteService.guardarEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudianteDTO);
    }

    // Actualizar Estudiante (PUT)
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        // Actualizar el estudiante y devolver el EstudianteDTO
        EstudianteDTO estudianteActualizadoDTO = estudianteService.actualizarEstudiante(id, estudiante);
        return ResponseEntity.ok(estudianteActualizadoDTO);
    }

    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}
