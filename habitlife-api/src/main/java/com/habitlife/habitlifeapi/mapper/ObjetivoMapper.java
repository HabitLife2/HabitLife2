package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.ObjetivoRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ObjetivoResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Objetivo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ObjetivoMapper {
    private final ModelMapper modelMapper;

    public Objetivo convertToEntity(ObjetivoRequestDTO objetivoRequestDTO){
        return modelMapper.map(objetivoRequestDTO, Objetivo.class);
    }

    public ObjetivoResponseDTO convertToDTO(Objetivo objetivo){
        return modelMapper.map(objetivo, ObjetivoResponseDTO.class);
    }

    public List<ObjetivoResponseDTO> convertToListDTO(List<Objetivo> objetivos){

        return objetivos.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
