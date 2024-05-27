package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.RutinaEjercicioResponseDTO;
import com.habitlife.habitlifeapi.service.RutinaEjercicioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutinas-ejercicio")
@AllArgsConstructor
public class RutinaEjercicioController {
    private final RutinaEjercicioService rutinaEjercicioService;

    @GetMapping
    public ResponseEntity<List<RutinaEjercicioResponseDTO>> getAllRutinas() {
        List<RutinaEjercicioResponseDTO> rutinas = rutinaEjercicioService.getAllRutinas();
        return new ResponseEntity<>(rutinas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RutinaEjercicioResponseDTO> getRutinaById(@PathVariable Long id) {
        RutinaEjercicioResponseDTO rutina = rutinaEjercicioService.getRutinaById(id);
        return new ResponseEntity<>(rutina, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RutinaEjercicioResponseDTO> createRutinaEjercicio(
                                                                            @RequestBody @Validated RutinaEjercicioRequestDTO rutinaEjercicioRequestDTO) {
        RutinaEjercicioResponseDTO rutina = rutinaEjercicioService.CrearRutinaEjercicio(rutinaEjercicioRequestDTO);
        return new ResponseEntity<>(rutina, HttpStatus.CREATED);
    }

    @PutMapping("/{rutinaid}")
    public ResponseEntity<RutinaEjercicioResponseDTO> updateRutinaEjercicio(
                                                                            @PathVariable Long rutinaid,
                                                                            @RequestBody @Validated RutinaEjercicioRequestDTO rutinaEjercicioRequestDTO) {
        RutinaEjercicioResponseDTO rutina = rutinaEjercicioService.ActualizarRutinaEjercicio(rutinaid, rutinaEjercicioRequestDTO);
        return new ResponseEntity<>(rutina, HttpStatus.OK);
    }

    @DeleteMapping("/{rutinaid}")
    public ResponseEntity<Void> deleteRutinaEjercicio(@PathVariable Long rutinaid) {
        rutinaEjercicioService.eliminarRutinaEjercicio(rutinaid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
