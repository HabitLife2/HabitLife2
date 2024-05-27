package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.ProfesionalMapper;
import com.habitlife.habitlifeapi.mapper.UsuarioMapper;
import com.habitlife.habitlifeapi.model.dto.ProfesionalRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ProfesionalResponseDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import com.habitlife.habitlifeapi.repository.ProfesionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;
    private final UsuarioMapper usuarioMapper;
    private final PlanNutricionalService planNutricionalService;
    private final RutinaEjercicioService rutinaEjercicioService;
    private final RutinaEstudioService rutinaEstudioService;

    @Transactional(readOnly = true)
    public List<ProfesionalResponseDTO> getAllProfesionals() {
        List<Profesional> profesionales = profesionalRepository.findAll();
        return profesionalMapper.convertToListDTO(profesionales);
    }

    @Transactional(readOnly = true)
    public ProfesionalResponseDTO getProfesionalById(Long id) {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el ID: " + id));
        return profesionalMapper.convertToDTO(profesional);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getAssignedUsers(Long id) {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + id));

        return profesional.getUsuarios().stream()
                .map(usuarioMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProfesionalResponseDTO createProfessional(ProfesionalRequestDTO profesionalRequestDTO) {
        if(profesionalRepository.existsByNombreEmailTelefonoProfesional(profesionalRequestDTO.getNombre(), profesionalRequestDTO.getEmail(), profesionalRequestDTO.getTelefono())) {
            throw new ResourceDuplicateException("Profesional ya existente con el nombre, email o telefono: " + profesionalRequestDTO.getNombre() + profesionalRequestDTO.getEmail()+ profesionalRequestDTO.getTelefono());
        }
        Profesional profesional = profesionalMapper.convertToEntity(profesionalRequestDTO);
        profesional.setFechaRegistro(LocalDate.now());
        profesional.setFechaActualizacion(LocalDate.now());
        profesionalRepository.save(profesional);
        return profesionalMapper.convertToDTO(profesional);
    }

    @Transactional

    public ProfesionalResponseDTO updateProfessional(Long id, ProfesionalRequestDTO profesionalRequestDTO) {
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el ID: " + id));

        // Actualizar los campos del profesional con los nuevos valores
        profesional.setNombre(profesionalRequestDTO.getNombre());
        profesional.setApellidoPaterno(profesionalRequestDTO.getApellidoPaterno());
        profesional.setApellidoMaterno(profesionalRequestDTO.getApellidoMaterno());
        profesional.setEmail(profesionalRequestDTO.getEmail());
        profesional.setAnios(profesionalRequestDTO.getAnios());
        profesional.setTelefono(profesionalRequestDTO.getTelefono());
        profesional.setEspecialidad(profesionalRequestDTO.getEspecialidad());
        profesional.setFechaActualizacion(LocalDate.now());

        // Guardar los cambios en la base de datos
        profesional = profesionalRepository.save(profesional);

        return profesionalMapper.convertToDTO(profesional);
    }

    @Transactional

    public void deleteProfesional(Long id) {
        if (!profesionalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Profesional no encontrado con el ID: " + id);
        }
        profesionalRepository.deleteById(id);
    }


}
