package com.habitlife.habitlifeapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoReportDTO {
    private LocalDate fechaPago;
    private long pagoCount;
}
