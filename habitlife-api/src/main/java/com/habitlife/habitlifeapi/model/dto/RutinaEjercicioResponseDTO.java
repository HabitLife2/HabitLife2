package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaEjercicioResponseDTO {
    private Long id;
    private UserType tipoUsuario;
    private Long usuarioId;
    private String nombreUsuario; // Nombre del usuario para facilitar la lectura
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int series;
    private int repeticiones;
    private Long profesionalId;
    private String nombreProfesional; // Nombre del profesional para facilitar la lectura
    private LocalDate fechaRegistro;
    private LocalDate fechaActualizacion;
}
