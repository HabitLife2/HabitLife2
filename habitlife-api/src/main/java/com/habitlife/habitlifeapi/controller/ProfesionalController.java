package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.ProfesionalRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ProfesionalResponseDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioResponseDTO;
import com.habitlife.habitlifeapi.service.ProfesionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesionales")
@AllArgsConstructor
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    @GetMapping
    public ResponseEntity<List<ProfesionalResponseDTO>> getAllProfesionals() {
        List<ProfesionalResponseDTO> profesionales = profesionalService.getAllProfesionals();
        return new ResponseEntity<>(profesionales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalResponseDTO> getProfesionalById(@PathVariable Long id) {
        ProfesionalResponseDTO profesional = profesionalService.getProfesionalById(id);
        return new ResponseEntity<>(profesional, HttpStatus.OK);
    }

    @GetMapping("/{id}/usuarios")
    public ResponseEntity<List<UsuarioResponseDTO>> getAssignedUsers(@PathVariable Long id) {
        List<UsuarioResponseDTO> usuarios = profesionalService.getAssignedUsers(id);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfesionalResponseDTO> createProfessional(@RequestBody @Validated ProfesionalRequestDTO profesionalRequestDTO) {
        ProfesionalResponseDTO profesional = profesionalService.createProfessional(profesionalRequestDTO);
        return new ResponseEntity<>(profesional, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesionalResponseDTO> updateProfessional(@PathVariable Long id, @RequestBody @Validated ProfesionalRequestDTO profesionalRequestDTO) {
        ProfesionalResponseDTO profesional = profesionalService.updateProfessional(id, profesionalRequestDTO);
        return new ResponseEntity<>(profesional, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesional(@PathVariable Long id) {
        profesionalService.deleteProfesional(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
