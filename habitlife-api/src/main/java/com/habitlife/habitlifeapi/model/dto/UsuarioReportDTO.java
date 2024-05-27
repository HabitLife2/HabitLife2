package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.entity.Objetivo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioReportDTO {
    private long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;

    // Campos adicionales para reportes
    private int numeroDeObjetivos;
    private int numeroDePlanesNutricionales;
    private int numeroDeRutinasEjercicio;
    private int numeroDeRutinasEstudio;
}
