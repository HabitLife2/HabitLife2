package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {
    @Query("SELECT COUNT(p) > 0 FROM Objetivo p WHERE p.nombre = :nombre")
    boolean existsByNombre(@Param("nombre") String nombre);

    boolean existsById(Long id);

    List<Objetivo> findByNombre(String nombre);

    // buscar objetivos por descripción que contenga una cadena específica
    List<Objetivo> findByDescripcionContaining(String keyword);

    // buscar objetivos por rango de fechas de inicio y fin
    List<Objetivo> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);

}
