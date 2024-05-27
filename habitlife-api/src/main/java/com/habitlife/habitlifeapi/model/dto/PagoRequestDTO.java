package com.habitlife.habitlifeapi.model.dto;

import com.habitlife.habitlifeapi.model.enums.MetodosPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {
    @NotNull(message = "El ID del usuario no puede estar vacío")
    private Long usuarioId;

    @NotBlank(message = "El monto del pago no puede estar vacio")
    @Size(min=1, max=3, message = "El monto del pago debe tener entre 1 y 3 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El monto del pago debe contener solo digitos")
    private BigDecimal monto;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private MetodosPago metodoPago;
}
