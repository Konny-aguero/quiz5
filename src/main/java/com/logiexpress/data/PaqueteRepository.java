package com.logiexpress.data;

import com.logiexpress.domain.Estado;
import com.logiexpress.domain.Paquete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    @Query("SELECT p FROM Paquete p WHERE p.estado = :estado")
    Page<Paquete> findByEstadoPaginado(@Param("estado") Estado estado, Pageable pageable);
}