package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanNutricionalResponseDTO {
    private Long id;
    private UserType tipoUsuario;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String alimentos;
    private Long usuarioId;
    private String nombreUsuario; // Nombre del usuario para facilitar la lectura
    private Long profesionalId;
    private String nombreProfesional;
    private LocalDate fechaRegistro;
    private LocalDate fechaActualizacion;
}
