package com.habitlife.habitlifeapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesionales")
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "anios", nullable = false)
    private int anios;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDate fechaActualizacion;

    @Column(name = "telefono_id", nullable = false)
    private String telefono;

    @Column(name = "especialidad_id", nullable = false)
    private String especialidad;

    @ManyToMany(mappedBy = "profesionales")
    private List<Usuario> usuarios = new ArrayList<>();  // Lista de usuarios asignados a este profesional

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PlanNutricional> planesNutricionales;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RutinaEjercicio> rutinasEjercicios;

    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RutinaEstudio> rutinasEstudios;
}
