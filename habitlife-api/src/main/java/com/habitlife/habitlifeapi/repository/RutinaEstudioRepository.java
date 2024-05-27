package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.RutinaEstudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RutinaEstudioRepository extends JpaRepository<RutinaEstudio, Long> {
    boolean existsById(Long id);

    @Query("SELECT COUNT(r) > 0 FROM RutinaEstudio r WHERE r.nombre = :nombre")
    boolean existsByNombreRutina(@Param("nombre") String nombre);

    List<RutinaEstudio> findByUsuarioId(Long usuarioId);

    List<RutinaEstudio> findByNombre(String nombre);

    List<RutinaEstudio> findByDescripcionContaining(String keyword);

    List<RutinaEstudio> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<RutinaEstudio> findByProfesionalId(Long profesionalId);

    List<RutinaEstudio> findByHoras(int horas);

    List<RutinaEstudio> findByCurso(String curso);
}
