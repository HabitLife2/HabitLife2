package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.ProfesionalRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ProfesionalResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProfesionalMapper {
    private final ModelMapper modelMapper;

    public Profesional convertToEntity(ProfesionalRequestDTO ProfesionalRequestDTO){
        return modelMapper.map(ProfesionalRequestDTO, Profesional.class);
    }

    public ProfesionalResponseDTO convertToDTO(Profesional profesional){
        return modelMapper.map(profesional, ProfesionalResponseDTO.class);
    }

    public List<ProfesionalResponseDTO> convertToListDTO(List<Profesional> profesionales){

        return profesionales.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
