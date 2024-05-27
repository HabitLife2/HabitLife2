package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.entity.Profesional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private int anios;
    private List<ObjetivoResponseDTO> objetivos;
    private boolean premium;
    private String direccion;
    private String telefono;
    private BigDecimal saldo;
    private List<Profesional> profesionals;
}
