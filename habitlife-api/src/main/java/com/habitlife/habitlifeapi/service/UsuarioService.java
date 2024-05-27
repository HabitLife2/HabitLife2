package com.habitlife.habitlifeapi.service;

import com.habitlife.habitlifeapi.exception.ResourceDuplicateException;
import com.habitlife.habitlifeapi.exception.ResourceNotFoundException;
import com.habitlife.habitlifeapi.mapper.UsuarioMapper;
import com.habitlife.habitlifeapi.model.dto.UsuarioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioResponseDTO;
import com.habitlife.habitlifeapi.model.entity.Objetivo;
import com.habitlife.habitlifeapi.model.entity.Profesional;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import com.habitlife.habitlifeapi.repository.ProfesionalRepository;
import com.habitlife.habitlifeapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ProfesionalRepository profesionalRepository;

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.convertToListDTO(usuarios);
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO getUserById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));
        return usuarioMapper.convertToDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO createAccount(UsuarioRequestDTO usuarioRequestDTO) {
        if(usuarioRepository.existsByNombreEmailTelefonoUsuario(usuarioRequestDTO.getNombre(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getTelefono())) {
            throw new ResourceDuplicateException("Usuario ya existente con el nombre, email o telefono: " + usuarioRequestDTO.getNombre() + usuarioRequestDTO.getEmail()+ usuarioRequestDTO.getTelefono());
        }

        Usuario usuario = usuarioMapper.convertToEntity(usuarioRequestDTO);
        usuario.setFechaRegistro(LocalDate.now());
        usuarioRepository.save(usuario);
        return usuarioMapper.convertToDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateAccount(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));

        usuario.setNombre(usuarioRequestDTO.getNombre());
        usuario.setApellidoPaterno(usuarioRequestDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioRequestDTO.getApellidoMaterno());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setAnios(usuarioRequestDTO.getAnios());
        usuario.setDireccion(usuarioRequestDTO.getDireccion());
        usuario.setTelefono(usuarioRequestDTO.getTelefono());
        usuario.setFechaActualizacion(LocalDate.now());

        // Actualizar los objetivos del usuario
        if (usuarioRequestDTO.getObjetivos() != null && !usuarioRequestDTO.getObjetivos().isEmpty()) {
            List<Objetivo> nuevosObjetivos = usuarioRequestDTO.getObjetivos().stream()
                    .map(objetivoRequestDTO -> {
                        Objetivo objetivo = new Objetivo();
                        objetivo.setNombre(objetivoRequestDTO.getNombre());
                        objetivo.setTipoObjetivo(objetivoRequestDTO.getTipoObjetivo());
                        objetivo.setDescripcion(objetivoRequestDTO.getDescripcion());
                        objetivo.setEstadoObjetivo(objetivoRequestDTO.getEstadoObjetivo());
                        objetivo.setFechaInicio(objetivoRequestDTO.getFechaInicio());
                        objetivo.setFechaFin(objetivoRequestDTO.getFechaFin());
                        objetivo.setFrecuencia(objetivoRequestDTO.getFrecuencia());
                        objetivo.setUsuario(objetivoRequestDTO.getUsuario());
                        return objetivo;
                    })
                    .collect(Collectors.toList());
            usuario.setObjetivos(nuevosObjetivos);
        }

        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.convertToDTO(usuario);
    }

    @Transactional
    // Método para eliminar un usuario por ID
    public void deleteUser(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con el ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioResponseDTO asignarProfesional(Long usuarioid, Long profesionalId) {
        Usuario usuario = usuarioRepository.findById(usuarioid)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + usuarioid));

        Profesional profesional = profesionalRepository.findById(profesionalId)
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado con el ID: " + profesionalId));

        // Asignar el profesional al usuario
        if (!usuario.getProfesionales().contains(profesional)) {
            usuario.getProfesionales().add(profesional);
        } else {
            throw new ResourceDuplicateException("El profesional ya está asignado a este usuario.");
        }

        usuarioRepository.save(usuario);

        return usuarioMapper.convertToDTO(usuario);
    }

}
