package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionalResponseDTO {
    private long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String anios;
    private String email;
    private String especialidad;
    private List<Usuario> usuarios;
    private LocalDate fechaRegistro;
    private LocalDate fechaActualizacion;

}
