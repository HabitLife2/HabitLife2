package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.Frecuencia;
import com.habitlife.habitlifeapi.model.enums.Status;
import com.habitlife.habitlifeapi.model.enums.TipoObjetivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjetivoRequestDTO {

    private Long usuarioId;

    @NotBlank(message = "El nombre del objetivo no puede estar vacio")
    private String nombre;

    @NotNull(message = "El tipo de objetivo no puede estar vacío")
    private TipoObjetivo tipoObjetivo;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    @NotNull(message = "El estado del objetivo no puede estar vacío")
    private Status estadoObjetivo;

    @NotBlank(message = "La fecha de inicio no puede estar vacia")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    @NotBlank(message = "La fecha de fin no puede estar vacia")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFin;

    @NotNull(message = "La frecuencia no puede estar vacía")
    private Frecuencia frecuencia;

}
