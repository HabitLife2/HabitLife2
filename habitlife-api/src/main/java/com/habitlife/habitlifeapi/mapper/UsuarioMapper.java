package com.habitlife.habitlifeapi.mapper;

import com.habitlife.habitlifeapi.model.dto.UsuarioReportDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UsuarioMapper {
    private final ModelMapper modelMapper;

    public Usuario convertToEntity(UsuarioRequestDTO usuarioRequestDTO){
        return modelMapper.map(usuarioRequestDTO, Usuario.class);
    }

    public UsuarioResponseDTO convertToDTO(Usuario usuario){
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public UsuarioReportDTO convertToReportDTO(Usuario usuario){
        UsuarioReportDTO usuarioReportDTO = modelMapper.map(usuario, UsuarioReportDTO.class);
        usuarioReportDTO.setNumeroDeObjetivos(usuario.getObjetivos().size());
        usuarioReportDTO.setNumeroDePlanesNutricionales(usuario.getPlanesNutricionales().size());
        usuarioReportDTO.setNumeroDeRutinasEjercicio(usuario.getRutinasEjercicios().size());
        usuarioReportDTO.setNumeroDeRutinasEstudio(usuario.getRutinasEstudios().size());
        return usuarioReportDTO;
    }

    public List<UsuarioResponseDTO> convertToListDTO(List<Usuario> usuarios){

        return usuarios.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
