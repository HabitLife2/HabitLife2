package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.MetodosPago;
import com.habitlife.habitlifeapi.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private Long id;
    private Long usuarioId;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private Status estadoPago;
    private MetodosPago metodoPago;
}
