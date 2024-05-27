package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RutinaEstudioRequestDTO {
    @Enumerated(EnumType.STRING)
    private UserType tipoUsuario;

    @NotBlank(message = "El nombre de la rutina no puede estar vacio")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotBlank(message = "La fecha inicio no puede estar vacia")
    @DateTimeFormat
    private LocalDate fechaInicio;

    @NotBlank(message = "La fecha final no puede estar vacia")
    @DateTimeFormat
    private LocalDate fechaFin;

    @Min(value = 0, message = "La cantidad de horas no puede ser negativa")
    @Max(value = 99, message = "La cantidad de horas no puede ser mayor a 99")
    private int horas;

    @NotBlank(message = "El nombre del curso no puede estar vacio")
    private String curso;

    private Long usuarioId;

    private Long profesionalId;
}
