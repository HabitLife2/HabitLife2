package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.Frecuencia;
import com.habitlife.habitlifeapi.model.enums.Status;
import com.habitlife.habitlifeapi.model.enums.TipoObjetivo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetivoResponseDTO {
    private long id;
    private Long usuarioId;
    private String nombre;
    private TipoObjetivo tipoObjetivo;
    private Status estadoObjetivo;
    private LocalDate fechaInicio;
    private Local fechaFin;
    private Frecuencia frecuencia;
}
