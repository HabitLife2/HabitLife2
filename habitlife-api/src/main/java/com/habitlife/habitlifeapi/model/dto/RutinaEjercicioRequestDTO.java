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
public class RutinaEjercicioRequestDTO {
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

    @Min(value = 0, message = "La edad del usuario no puede ser negativa")
    @Max(value = 99, message = "La edad del usuario no puede ser mayor a 99")
    private int series;

    @Min(value = 0, message = "La edad del usuario no puede ser negativa")
    @Max(value = 99, message = "La edad del usuario no puede ser mayor a 99")
    private int repeticiones;

    private Long usuarioId;

    private Long profesionalId;
}
