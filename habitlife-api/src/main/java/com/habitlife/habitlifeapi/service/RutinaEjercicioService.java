package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.RutinaEjercicioMapper;
import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import com.habitlife.habitlifeapi.model.entity.RutinaEjercicio;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import com.habitlife.habitlifeapi.model.enums.UserType;
import com.habitlife.habitlifeapi.repository.ProfesionalRepository;
import com.habitlife.habitlifeapi.repository.RutinaEjercicioRepository;
import com.habitlife.habitlifeapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RutinaEjercicioService {
    private final RutinaEjercicioRepository rutinaEjercicioRepository;
    private final ProfesionalRepository profesionalRepository;
    private final UsuarioRepository usuarioRepository;
    private final RutinaEjercicioMapper rutinaEjercicioMapper;

    @Transactional(readOnly = true)
    public List<RutinaEjercicioResponseDTO> getAllRutinas() {
        List<RutinaEjercicio> rutinasejercicios = rutinaEjercicioRepository.findAll();
        return rutinaEjercicioMapper.convertToListDTO(rutinasejercicios);
    }

    @Transactional(readOnly = true)
    public RutinaEjercicioResponseDTO getRutinaById(Long id) {
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de ejercicio no encontrada con el ID: " + id));
        return rutinaEjercicioMapper.convertToDTO(rutinaEjercicio);
    }

    @Transactional
    public RutinaEjercicioResponseDTO CrearRutinaEjercicio(RutinaEjercicioRequestDTO rutinaEjercicioRequestDTO) {
        Usuario usuario = null;
        Profesional profesional = null;
        if (rutinaEjercicioRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            usuario = usuarioRepository.findById(rutinaEjercicioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + rutinaEjercicioRequestDTO.getUsuarioId()));
            rutinaEjercicioRequestDTO.setUsuarioId(rutinaEjercicioRequestDTO.getUsuarioId());
        } else if (rutinaEjercicioRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            profesional = profesionalRepository.findById(rutinaEjercicioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + rutinaEjercicioRequestDTO.getUsuarioId()));
            rutinaEjercicioRequestDTO.setProfesionalId(rutinaEjercicioRequestDTO.getUsuarioId());
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        if(rutinaEjercicioRepository.existsByNombreRutina(rutinaEjercicioRequestDTO.getNombre())) {
            throw new ResourceDuplicateException("Rutina de Ejercicio ya existente con el id: " + rutinaEjercicioRequestDTO.getNombre());
        }
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioMapper.convertToEntity(rutinaEjercicioRequestDTO);
        rutinaEjercicio.setFechaRegistro(LocalDate.now());
        rutinaEjercicio.setFechaActualizacion(LocalDate.now());
        rutinaEjercicioRepository.save(rutinaEjercicio);
        return rutinaEjercicioMapper.convertToDTO(rutinaEjercicio);
    }

    @Transactional
    public RutinaEjercicioResponseDTO ActualizarRutinaEjercicio(Long rutinaid, RutinaEjercicioRequestDTO rutinaEjercicioRequestDTO) {
        Usuario usuario = null;
        Profesional profesional = null;
        if (rutinaEjercicioRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            usuario = usuarioRepository.findById(rutinaEjercicioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + rutinaEjercicioRequestDTO.getUsuarioId()));
            rutinaEjercicioRequestDTO.setUsuarioId(rutinaEjercicioRequestDTO.getUsuarioId());
        } else if (rutinaEjercicioRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            profesional = profesionalRepository.findById(rutinaEjercicioRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + rutinaEjercicioRequestDTO.getUsuarioId()));
            rutinaEjercicioRequestDTO.setProfesionalId(rutinaEjercicioRequestDTO.getUsuarioId());
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        RutinaEjercicio rutinaEjercicio = rutinaEjercicioRepository.findById(rutinaid)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de Ejercicio no encontrado con el id: " + rutinaid));

        rutinaEjercicioMapper.convertToEntity(rutinaEjercicioRequestDTO);
        rutinaEjercicio.setProfesional(rutinaEjercicio.getProfesional());
        rutinaEjercicio.setUsuario(rutinaEjercicio.getUsuario());
        rutinaEjercicio.setNombre(rutinaEjercicio.getNombre());
        rutinaEjercicio.setDescripcion(rutinaEjercicio.getDescripcion());
        rutinaEjercicio.setFechaInicio(rutinaEjercicio.getFechaInicio());
        rutinaEjercicio.setFechaFin(rutinaEjercicio.getFechaFin());
        rutinaEjercicio.setSeries(rutinaEjercicio.getSeries());
        rutinaEjercicio.setRepeticiones(rutinaEjercicio.getRepeticiones());
        rutinaEjercicio.setFechaActualizacion(LocalDate.now());

        rutinaEjercicioRepository.save(rutinaEjercicio);
        return rutinaEjercicioMapper.convertToDTO(rutinaEjercicio);
    }

    @Transactional
    public void eliminarRutinaEjercicio(Long rutinaid) {
        RutinaEjercicio rutinaEjercicio = rutinaEjercicioRepository.findById(rutinaid)
                .orElseThrow(() -> new ResourceNotFoundException("Rutina de ejercicio no encontrado con el ID: " + rutinaid));

        rutinaEjercicioRepository.delete(rutinaEjercicio);
    }
}
