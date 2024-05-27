package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.RutinaEstudioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEstudioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.RutinaEstudio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RutinaEstudioMapper {
    private final ModelMapper modelMapper;

    public RutinaEstudio convertToEntity(RutinaEstudioRequestDTO RutinaEstudioRequestDTO){
        return modelMapper.map(RutinaEstudioRequestDTO, RutinaEstudio.class);
    }

    public RutinaEstudioResponseDTO convertToDTO(RutinaEstudio rutinaEstudio) {
        RutinaEstudioResponseDTO responseDTO = modelMapper.map(rutinaEstudio, RutinaEstudioResponseDTO.class);
        responseDTO.setUsuarioId(rutinaEstudio.getUsuario().getId());
        responseDTO.setNombreUsuario(rutinaEstudio.getUsuario().getNombre() + " " +
                rutinaEstudio.getUsuario().getApellidoPaterno() + " " +
                rutinaEstudio.getUsuario().getApellidoMaterno());
        if (rutinaEstudio.getProfesional() != null) {
            responseDTO.setProfesionalId(rutinaEstudio.getProfesional().getId());
            responseDTO.setNombreProfesional(rutinaEstudio.getProfesional().getNombre() + " " +
                    rutinaEstudio.getProfesional().getApellidoPaterno() + " " +
                    rutinaEstudio.getProfesional().getApellidoMaterno());
        }
        return responseDTO;
    }

    public List<RutinaEstudioResponseDTO> convertToListDTO(List<RutinaEstudio> rutinaestudios){

        return rutinaestudios.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
