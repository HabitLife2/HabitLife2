package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.RutinaEstudioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEstudioResponseDTO;
import com.habitlife.habitlifeapi.service.RutinaEstudioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutinas-estudio")
@AllArgsConstructor
public class RutinaEstudioController {
    private final RutinaEstudioService rutinaEstudioService;

    @GetMapping
    public ResponseEntity<List<RutinaEstudioResponseDTO>> getAllRutinas() {
        List<RutinaEstudioResponseDTO> rutinas = rutinaEstudioService.getAllRutinas();
        return new ResponseEntity<>(rutinas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaEstudioResponseDTO> getRutinaById(@PathVariable Long id) {
        RutinaEstudioResponseDTO rutina = rutinaEstudioService.getRutinaById(id);
        return new ResponseEntity<>(rutina, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RutinaEstudioResponseDTO> createRutinaEstudio(
                                                                        @PathVariable Long rutinaid,
                                                                        @RequestBody @Validated RutinaEstudioRequestDTO rutinaEstudioRequestDTO) {
        RutinaEstudioResponseDTO rutina = rutinaEstudioService.CrearRutinaEstudio(rutinaid, rutinaEstudioRequestDTO);
        return new ResponseEntity<>(rutina, HttpStatus.CREATED);
    }

    @PutMapping("/{rutinaid}")
    public ResponseEntity<RutinaEstudioResponseDTO> updateRutinaEstudio(
                                                                        @PathVariable Long rutinaid,
                                                                        @RequestBody @Validated RutinaEstudioRequestDTO rutinaEstudioRequestDTO) {
        RutinaEstudioResponseDTO rutina = rutinaEstudioService.ActualizarRutinaEstudio(rutinaid, rutinaEstudioRequestDTO);
        return new ResponseEntity<>(rutina, HttpStatus.OK);
    }

    @DeleteMapping("/{rutinaid}")
    public ResponseEntity<Void> deleteRutinaEstudio(@PathVariable Long rutinaid) {
        rutinaEstudioService.eliminarRutinaEstudio(rutinaid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
