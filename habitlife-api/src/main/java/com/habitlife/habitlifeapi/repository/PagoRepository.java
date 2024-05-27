package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.Pago;
import com.habitlife.habitlifeapi.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByUsuarioId(Long usuario);

    List<Pago> findByEstadoPago(Status estadoPago);

    boolean existsById(Long id);

    @Query("SELECT p FROM Pago p WHERE p.fechaPago BETWEEN :startDate AND :endDate")
    List<Pago> findPagosBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT p FROM Pago p WHERE p.metodoPago = :metodoPago")
    List<Pago> findByMetodoPago(@Param("metodoPago") String metodoPago);

    @Query("SELECT c.fechaPago,COUNT(c) FROM Pago c WHERE c.fechaPago BETWEEN :startDate AND :endDate AND (c.usuario.id=:usuarioid) GROUP BY c.fechaPago")
    List<Object[]> getPagosCountByDateRangeAndId(@Param("startDate")LocalDate startDate,
                                                                  @Param("endDate")LocalDate endDate,
                                                                  @Param("usuarioid") String usuarioid);
}
