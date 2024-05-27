package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanNutricionalRequestDTO {
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserType tipoUsuario;

    private Long usuarioId;

    private Long profesionalId;

    @NotBlank(message = "El nombre del plan nutricional no puede estar vacio")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message = "La fecha de inicio no puede estar vacia")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede estar vacia")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFin;

    @NotBlank(message = "Los alimentos no pueden estar vacios")
    @Size(min=5, max=100, message = "Los alimentos deben tener entre 5 a 100 caracteres")
    private String alimentos;

}
