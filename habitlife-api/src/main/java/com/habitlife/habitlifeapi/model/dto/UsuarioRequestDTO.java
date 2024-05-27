package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.entity.Objetivo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank(message = "El nombre del usuario no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido paterno del usuario no puede estar vacio")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno del usuario no puede estar vacio")
    private String apellidoMaterno;

    @NotBlank(message = "El correo del usuario no puede estar vacio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @Min(value = 0, message = "La edad del usuario no puede ser negativa")
    @Max(value = 99, message = "La edad del usuario no puede ser mayor a 99")
    private int anios;

    private List<Objetivo> objetivos;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    private String telefono;
}
