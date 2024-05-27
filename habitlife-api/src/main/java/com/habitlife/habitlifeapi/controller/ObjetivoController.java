package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.ObjetivoRequestDTO;
import com.habitlife.habitlifeapi.model.dto.ObjetivoResponseDTO;
import com.habitlife.habitlifeapi.service.ObjetivoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/objetivos")
@AllArgsConstructor
public class ObjetivoController {
    private final ObjetivoService objetivoService;

    @GetMapping
    public ResponseEntity<List<ObjetivoResponseDTO>> getAllObjetivos() {
        List<ObjetivoResponseDTO> objetivos = objetivoService.getAllObjetivos();
        return new ResponseEntity<>(objetivos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivoResponseDTO> getObjetivoById(@PathVariable Long id) {
        ObjetivoResponseDTO objetivo = objetivoService.getObjetivoById(id);
        return new ResponseEntity<>(objetivo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ObjetivoResponseDTO> createObjetivo(
                                                              @RequestBody @Validated ObjetivoRequestDTO objetivoRequestDTO) {
        ObjetivoResponseDTO objetivo = objetivoService.createObjetivo(objetivoRequestDTO);
        return new ResponseEntity<>(objetivo, HttpStatus.CREATED);
    }

    @PutMapping("/{objetivoId}")
    public ResponseEntity<ObjetivoResponseDTO> updateObjetivo(
                                                              @PathVariable Long objetivoId,
                                                              @RequestBody @Validated ObjetivoRequestDTO objetivoRequestDTO) {
        ObjetivoResponseDTO objetivo = objetivoService.updateObjetivo(objetivoId, objetivoRequestDTO);
        return new ResponseEntity<>(objetivo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjetivo(@PathVariable Long id) {
        objetivoService.deleteObjetivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
