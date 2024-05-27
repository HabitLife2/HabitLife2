package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.RutinaEstudioMapper;
import com.habitlife.habitlifeapi.model.dto.RutinaEstudioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEstudioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import com.habitlife.habitlifeapi.model.entity.RutinaEstudio;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import com.habitlife.habitlifeapi.model.enums.UserType;
import com.habitlife.habitlifeapi.repository.ProfesionalRepository;
import com.habitlife.habitlifeapi.repository.RutinaEstudioRepository;
import com.habitlife.habitlifeapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RutinaEstudioService {
    private final RutinaEstudioRepository rutinaEstudioRepository;
    private final ProfesionalRepository profesionalRepository;
    private final UsuarioRepository usuarioRepository;
    private final RutinaEstudioMapper rutinaEstudioMapper;

    @Transactional(readOnly = true)
    public List<RutinaEstudioResponseDTO> getAllRutinas() {
        List<RutinaEstudio> rutinasestudios = rutinaEstudioRepository.findAll();
        return rutinaEstudioMapper.convertToListDTO(rutinasestudios);
    }

    @Transactional(readOnly = true)
    public RutinaEstudioResponseDTO getRutinaById(Long id) {
        RutinaEstudio rutinaEstudio = rutinaEstudioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de estudio no encontrada con el ID: " + id));
        return rutinaEstudioMapper.convertToDTO(rutinaEstudio);
    }

    @Transactional
    public RutinaEstudioResponseDTO CrearRutinaEstudio(Long rutinaid, RutinaEstudioRequestDTO rutinaEstudioRequestDTO) {
        Usuario usuario = null;
        Profesional profesional = null;
        if (rutinaEstudioRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            usuario = usuarioRepository.findById(rutinaEstudioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + rutinaEstudioRequestDTO.getUsuarioId()));
            rutinaEstudioRequestDTO.setUsuarioId(rutinaEstudioRequestDTO.getUsuarioId());
        } else if (rutinaEstudioRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            profesional = profesionalRepository.findById(rutinaEstudioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + rutinaEstudioRequestDTO.getUsuarioId()));
            rutinaEstudioRequestDTO.setProfesionalId(rutinaEstudioRequestDTO.getUsuarioId());
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        if(rutinaEstudioRepository.existsByNombreRutina(rutinaEstudioRequestDTO.getNombre())) {
            throw new ResourceDuplicateException("Rutina de Ejercicio ya existente con el id: " + rutinaEstudioRequestDTO.getNombre());
        }
        RutinaEstudio rutinaEstudio = rutinaEstudioMapper.convertToEntity(rutinaEstudioRequestDTO);
        rutinaEstudio.setFechaRegistro(LocalDate.now());
        rutinaEstudio.setFechaActualizacion(LocalDate.now());
        rutinaEstudioRepository.save(rutinaEstudio);
        return rutinaEstudioMapper.convertToDTO(rutinaEstudio);
    }

    @Transactional
    public RutinaEstudioResponseDTO ActualizarRutinaEstudio(Long rutinaid, RutinaEstudioRequestDTO rutinaEstudioRequestDTO) {
        Usuario usuario = null;
        Profesional profesional = null;
        if (rutinaEstudioRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            usuario = usuarioRepository.findById(rutinaEstudioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + rutinaEstudioRequestDTO.getUsuarioId()));
            rutinaEstudioRequestDTO.setUsuarioId(rutinaEstudioRequestDTO.getUsuarioId());
        } else if (rutinaEstudioRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            profesional = profesionalRepository.findById(rutinaEstudioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + rutinaEstudioRequestDTO.getUsuarioId()));
            rutinaEstudioRequestDTO.setProfesionalId(rutinaEstudioRequestDTO.getUsuarioId());
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        RutinaEstudio rutinaEstudio = rutinaEstudioRepository.findById(rutinaid)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de Estudio no encontrado con el id: " + rutinaid));

        rutinaEstudioMapper.convertToEntity(rutinaEstudioRequestDTO);
        rutinaEstudio.setProfesional(rutinaEstudio.getProfesional());
        rutinaEstudio.setUsuario(rutinaEstudio.getUsuario());
        rutinaEstudio.setNombre(rutinaEstudio.getNombre());
        rutinaEstudio.setDescripcion(rutinaEstudio.getDescripcion());
        rutinaEstudio.setFechaInicio(rutinaEstudio.getFechaInicio());
        rutinaEstudio.setFechaFin(rutinaEstudio.getFechaFin());
        rutinaEstudio.setHoras(rutinaEstudio.getHoras());
        rutinaEstudio.setCurso(rutinaEstudio.getCurso());
        rutinaEstudio.setFechaActualizacion(LocalDate.now());

        rutinaEstudioRepository.save(rutinaEstudio);
        return rutinaEstudioMapper.convertToDTO(rutinaEstudio);
    }

    @Transactional
    public void eliminarRutinaEstudio(Long rutinaid) {
        RutinaEstudio rutinaEstudio = rutinaEstudioRepository.findById(rutinaid)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de estudio no encontrado con el ID: " + rutinaid));

        rutinaEstudioRepository.delete(rutinaEstudio);
    }
}
