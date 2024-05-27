package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.PlanNutricionalMapper;
import com.habitlife.habitlifeapi.model.dto.PlanNutricionalRequestDTO;
import com.habitlife.habitlifeapi.model.dto.PlanNutricionalResponseDTO;
import com.habitlife.habitlifeapi.model.entity.PlanNutricional;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import com.habitlife.habitlifeapi.model.enums.UserType;
import com.habitlife.habitlifeapi.repository.PlanNutricionalRepository;
import com.habitlife.habitlifeapi.repository.ProfesionalRepository;
import com.habitlife.habitlifeapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PlanNutricionalService {
    private final PlanNutricionalRepository planNutricionalRepository;
    private final ProfesionalRepository profesionalRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanNutricionalMapper planNutricionalMapper;

    @Transactional(readOnly = true)
    public List<PlanNutricionalResponseDTO> getAllPlanes() {
        List<PlanNutricional> planesnutricionales = planNutricionalRepository.findAll();
        return planNutricionalMapper.convertToListDTO(planesnutricionales);
    }

    @Transactional(readOnly = true)
    public PlanNutricionalResponseDTO getPlanById(Long id) {
        PlanNutricional planNutricional = planNutricionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan Nutricional no encontrado con el ID: " + id));
        return planNutricionalMapper.convertToDTO(planNutricional);
    }

    @Transactional
    public PlanNutricionalResponseDTO ActualizarPlanNutricional(Long id, PlanNutricionalRequestDTO planNutricionalRequestDTO) {
        if (planNutricionalRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            Usuario usuario = usuarioRepository.findById(planNutricionalRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + planNutricionalRequestDTO.getUsuarioId()));
            planNutricionalRequestDTO.setUsuarioId(planNutricionalRequestDTO.getUsuarioId());
        } else if (planNutricionalRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            Profesional profesional = profesionalRepository.findById(planNutricionalRequestDTO.getProfesionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + planNutricionalRequestDTO.getProfesionalId()));
            planNutricionalRequestDTO.setProfesionalId(planNutricionalRequestDTO.getProfesionalId());
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        PlanNutricional planNutricional = planNutricionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan Nutricional no encontrado con el id: " + id));

        planNutricionalMapper.convertToEntity(planNutricionalRequestDTO);
        planNutricional.setProfesional(planNutricional.getProfesional());
        planNutricional.setUsuario(planNutricional.getUsuario());
        planNutricional.setNombre(planNutricional.getNombre());
        planNutricional.setDescripcion(planNutricional.getDescripcion());
        planNutricional.setFechaInicio(planNutricional.getFechaInicio());
        planNutricional.setFechaFin(planNutricional.getFechaFin());
        planNutricional.setAlimentos(planNutricional.getAlimentos());
        planNutricional.setFechaActualizacion(LocalDate.now());

        planNutricionalRepository.save(planNutricional);
        return planNutricionalMapper.convertToDTO(planNutricional);
    }

    @Transactional
    public PlanNutricionalResponseDTO CrearPlanNutricional(PlanNutricionalRequestDTO planNutricionalRequestDTO) {
        Usuario usuario = null;
        Profesional profesional = null;

        if (planNutricionalRequestDTO.getTipoUsuario() == UserType.USUARIO) {
            usuario = usuarioRepository.findById(planNutricionalRequestDTO.getUsuarioId())
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el id: " + planNutricionalRequestDTO.getUsuarioId()));
        } else if (planNutricionalRequestDTO.getTipoUsuario() == UserType.PROFESIONAL) {
            profesional = profesionalRepository.findById(planNutricionalRequestDTO.getProfesionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el id: " + planNutricionalRequestDTO.getProfesionalId()));
        } else {
            throw new IllegalArgumentException("Tipo de usuario no soportado");
        }

        if (planNutricionalRepository.existsByNombrePlan(planNutricionalRequestDTO.getNombre())) {
            throw new ResourceDuplicateException("Plan Nutricional ya existente con el nombre: " + planNutricionalRequestDTO.getNombre());
        }

        PlanNutricional planNutricional = planNutricionalMapper.convertToEntity(planNutricionalRequestDTO);
        planNutricional.setFechaRegistro(LocalDate.now());
        planNutricional.setFechaActualizacion(LocalDate.now());

        if (usuario != null) {
            planNutricional.setUsuario(usuario);  // Asociar el usuario existente al plan nutricional
        }
        if (profesional != null) {
            planNutricional.setProfesional(profesional);  // Asociar el profesional existente al plan nutricional
        }

        planNutricionalRepository.save(planNutricional);
        return planNutricionalMapper.convertToDTO(planNutricional);
    }


    @Transactional
    public void eliminarPlanNutricional(Long planId) {
        PlanNutricional planNutricional = planNutricionalRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan Nutricional no encontrado con el ID: " + planId));

        planNutricionalRepository.delete(planNutricional);
    }
}
