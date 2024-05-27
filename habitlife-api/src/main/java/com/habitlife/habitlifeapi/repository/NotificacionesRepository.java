package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.Notificaciones;
import com.habitlife.habitlifeapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {
    // Buscar notificaciones por ID de usuario
    List<Notificaciones> findByUsuarioId(Long usuarioId);

    // Buscar notificaciones por fecha de envío
    List<Notificaciones> findByFechaEnvio(LocalDate fechaEnvio);

    // Buscar notificaciones por estado de leída
    List<Notificaciones> findByLeida(boolean leida);

    // Buscar notificaciones por parte del mensaje
    List<Notificaciones> findByMensajeContaining(String parteMensaje);
}
