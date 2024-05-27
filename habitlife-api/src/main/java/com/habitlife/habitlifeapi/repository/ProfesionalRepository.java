package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Profesional p WHERE p.nombre = :nombre AND p.email = :email AND p.telefono = :telefono")
    boolean existsByNombreEmailTelefonoProfesional(@Param("nombre") String nombre,
                                      @Param("email") String email,
                                      @Param("telefono") String telefono);

    boolean existsById(Long id);

    List<Profesional> findByNombre(String nombre);

    Profesional findByEmail(String email);

    List<Profesional> findByAnios(int anios);

    List<Profesional> findByEspecialidad(String especialidad);

    List<Profesional> findByFechaRegistro(LocalDate fechaRegistro);

    List<Profesional> findByPlanesNutricionales(PlanNutricional planNutricional);

    List<Profesional> findByRutinasEjercicios(RutinaEjercicio rutinaEjercicio);

    List<Profesional> findByRutinasEstudios(RutinaEstudio rutinaEstudio);
}
