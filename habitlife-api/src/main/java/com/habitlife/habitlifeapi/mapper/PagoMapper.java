package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.PagoReportDTO;
import com.habitlife.habitlifeapi.model.dto.PagoRequestDTO;
import com.habitlife.habitlifeapi.model.dto.PagoResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Pago;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class PagoMapper {
    private final ModelMapper modelMapper;

    public Pago convertToEntity(PagoRequestDTO PagoRequestDTO){
        return modelMapper.map(PagoRequestDTO, Pago.class);
    }

    public PagoResponseDTO convertToDTO(Pago pago){
        return modelMapper.map(pago, PagoResponseDTO.class);
    }

    public List<PagoResponseDTO> convertToListDTO(List<Pago> pagos){

        return pagos.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PagoReportDTO convertPagoReportDTO(Object[] pagoData) {
        PagoReportDTO reportDTO = new PagoReportDTO();
        reportDTO.setFechaPago((LocalDate) pagoData[0]);
        reportDTO.setPagoCount((Long) pagoData[1]);
        return reportDTO;
    }
}
