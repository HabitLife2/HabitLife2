package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.ObjetivoMapper;
import com.habitlife.habitlifeapi.model.dto.ObjetivoRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ObjetivoResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Objetivo;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import com.habitlife.habitlifeapi.repository.ObjetivoRepository;
import com.habitlife.habitlifeapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ObjetivoService {
    private final ObjetivoRepository objetivoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ObjetivoMapper objetivoMapper;

    @Transactional(readOnly = true)
    public List<ObjetivoResponseDTO> getAllObjetivos() {
        List<Objetivo> objetivos = objetivoRepository.findAll();
        return objetivoMapper.convertToListDTO(objetivos);
    }

    @Transactional(readOnly = true)
    public ObjetivoResponseDTO getObjetivoById(Long id) {
        Objetivo objetivo = objetivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Objetivo no encontrado con el ID: " + id));
        return objetivoMapper.convertToDTO(objetivo);
    }

    @Transactional
    public ObjetivoResponseDTO createObjetivo(ObjetivoRequestDTO objetivoRequestDTO) {
        Usuario usuario = usuarioRepository.findById(objetivoRequestDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + objetivoRequestDTO.getUsuarioId()));

        if(objetivoRepository.existsByNombre(objetivoRequestDTO.getNombre())) {
            throw new ResourceDuplicateException("Objetivo ya existente con el nombre: " + objetivoRequestDTO.getNombre());
        }
        Objetivo objetivo = objetivoMapper.convertToEntity(objetivoRequestDTO);
        objetivo.setUsuario(usuario);
        objetivo.setFechaRegistro(LocalDate.now());
        objetivo.setFechaActualizacion(LocalDate.now());

        objetivo = objetivoRepository.save(objetivo);

        return objetivoMapper.convertToDTO(objetivo);
    }

    @Transactional
    public ObjetivoResponseDTO updateObjetivo(Long objetivoId, ObjetivoRequestDTO objetivoRequestDTO) {
        Usuario usuario = usuarioRepository.findById(objetivoRequestDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + objetivoRequestDTO.getUsuarioId()));

        Objetivo objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new ResourceNotFoundException("Objetivo no encontrado con el ID: " + objetivoId));

        objetivo.setNombre(objetivoRequestDTO.getNombre());
        objetivo.setTipoObjetivo(objetivoRequestDTO.getTipoObjetivo());
        objetivo.setDescripcion(objetivoRequestDTO.getDescripcion());
        objetivo.setEstadoObjetivo(objetivoRequestDTO.getEstadoObjetivo());
        objetivo.setFechaInicio(objetivoRequestDTO.getFechaInicio());
        objetivo.setFechaFin(objetivoRequestDTO.getFechaFin());
        objetivo.setFrecuencia(objetivoRequestDTO.getFrecuencia());
        objetivo.setFechaActualizacion(LocalDate.now());

        objetivo = objetivoRepository.save(objetivo);

        return objetivoMapper.convertToDTO(objetivo);
    }

    @Transactional
    public void deleteObjetivo(Long id) {
        if (!objetivoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Objetivo no encontrado con el ID: " + id);
        }
        objetivoRepository.deleteById(id);
    }
}
