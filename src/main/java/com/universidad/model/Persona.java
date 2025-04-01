package com.universidad.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class Persona {
    private String nombre;
    private String apellido;
    private String email;
    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;
}
