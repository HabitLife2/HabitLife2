package com.habitlife.habitlifeapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalRequestDTO {
    @NotBlank(message = "El nombre del usuario no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido paterno del usuario no puede estar vacio")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno del usuario no puede estar vacio")
    private String apellidoMaterno;

    @Min(value = 0, message = "La edad del usuario no puede ser negativa")
    @Max(value = 99, message = "La edad del usuario no puede ser mayor a 99")
    private int anios;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String contrasena;

    @NotBlank(message = "La especialidad no puede estar vacio")
    private String telefono;

    @NotBlank(message = "La especialidad no puede estar vacio")
    private String especialidad;


}
