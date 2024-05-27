package com.habitlife.habitlifeapi.repository;

import com.habitlife.habitlifeapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.nombre = :nombre AND u.email = :email AND u.telefono = :telefono")
    boolean existsByNombreEmailTelefonoUsuario(@Param("nombre") String nombre,
                                                   @Param("email") String email,
                                                   @Param("telefono") String telefono);

    boolean existsById(Long id);

    List<Usuario> findByNombre(String nombre);

    List<Usuario> findByEmail(String email);

    List<Usuario> findByAnios(int anios);

    List<Usuario> findByFechaRegistro(LocalDate fechaRegistro);

    List<Usuario> findByPremiumTrue();
}
