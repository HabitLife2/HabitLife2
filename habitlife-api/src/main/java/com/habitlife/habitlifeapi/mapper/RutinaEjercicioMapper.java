package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.RutinaEjercicio;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RutinaEjercicioMapper {
    private final ModelMapper modelMapper;

    public RutinaEjercicio convertToEntity(RutinaEjercicioRequestDTO RutinaEjercicioRequestDTO){
        return modelMapper.map(RutinaEjercicioRequestDTO, RutinaEjercicio.class);
    }

    public RutinaEjercicioResponseDTO convertToDTO(RutinaEjercicio rutinaEjercicio) {
        RutinaEjercicioResponseDTO responseDTO = modelMapper.map(rutinaEjercicio, RutinaEjercicioResponseDTO.class);
        responseDTO.setUsuarioId(rutinaEjercicio.getUsuario().getId());
        responseDTO.setNombreUsuario(rutinaEjercicio.getUsuario().getNombre() + " " +
                rutinaEjercicio.getUsuario().getApellidoPaterno() + " " +
                rutinaEjercicio.getUsuario().getApellidoMaterno());
        if (rutinaEjercicio.getProfesional() != null) {
            responseDTO.setProfesionalId(rutinaEjercicio.getProfesional().getId());
            responseDTO.setNombreProfesional(rutinaEjercicio.getProfesional().getNombre() + " " +
                    rutinaEjercicio.getProfesional().getApellidoPaterno() + " " +
                    rutinaEjercicio.getProfesional().getApellidoMaterno());
        }
        return responseDTO;
    }

    public List<RutinaEjercicioResponseDTO> convertToListDTO(List<RutinaEjercicio> rutinaejercicios){

        return rutinaejercicios.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
