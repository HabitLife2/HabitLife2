package com.habitlife.habitlifeapi.controller;

import com.habitlife.habitlifeapi.model.dto.UsuarioRequestDTO;
import com.habitlife.habitlifeapi.model.dto.UsuarioResponseDTO;
import com.habitlife.habitlifeapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsers() {
        List<UsuarioResponseDTO> usuarios = usuarioService.getAllUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUserById(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.getUserById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createAccount(@RequestBody @Validated UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuario = usuarioService.createAccount(usuarioRequestDTO);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateAccount(@PathVariable Long id, @RequestBody @Validated UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuario = usuarioService.updateAccount(id, usuarioRequestDTO);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/{profesionalId}")
    public ResponseEntity<UsuarioResponseDTO> asignarProfesional(
            @PathVariable Long id,
            @PathVariable Long profesionalId) {
        UsuarioResponseDTO responseDTO = usuarioService.asignarProfesional(id, profesionalId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
