package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.PlanNutricionalRequestDTO;
import com.habitlife.habitlifeapi.model.dto.PlanNutricionalResponseDTO;
import com.habitlife.habitlifeapi.service.PlanNutricionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planes-nutricionales")
@AllArgsConstructor
public class PlanNutricionalController {
    private final PlanNutricionalService planNutricionalService;

    @GetMapping
    public ResponseEntity<List<PlanNutricionalResponseDTO>> getAllPlanes() {
        List<PlanNutricionalResponseDTO> planes = planNutricionalService.getAllPlanes();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanNutricionalResponseDTO> getPlanById(@PathVariable Long id) {
        PlanNutricionalResponseDTO plan = planNutricionalService.getPlanById(id);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlanNutricionalResponseDTO> createPlanNutricional(@RequestBody @Validated PlanNutricionalRequestDTO planNutricionalRequestDTO) {
        PlanNutricionalResponseDTO plan = planNutricionalService.CrearPlanNutricional(planNutricionalRequestDTO);
        return new ResponseEntity<>(plan, HttpStatus.CREATED);
    }

    @PutMapping("/{planid}")
    public ResponseEntity<PlanNutricionalResponseDTO> updatePlanNutricional(
                                                                            @PathVariable Long planid,
                                                                            @RequestBody @Validated PlanNutricionalRequestDTO planNutricionalRequestDTO) {
        PlanNutricionalResponseDTO plan = planNutricionalService.ActualizarPlanNutricional(planid, planNutricionalRequestDTO);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    @DeleteMapping("/{planid}")
    public ResponseEntity<Void> deletePlanNutricional(@PathVariable Long planid) {
        planNutricionalService.eliminarPlanNutricional(planid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
