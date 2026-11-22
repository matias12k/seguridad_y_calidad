package com.duoc.seguridadcalidad.repository;

import com.duoc.seguridadcalidad.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByDestacadoTrue();

    @Query("SELECT e FROM Evento e WHERE " +
           "(:query IS NULL OR :query = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.videojuego) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.categoria) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.ciudad) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(e.pais) LIKE LOWER(CONCAT('%', :query, '%'))) AND " +
           "(:modalidad IS NULL OR :modalidad = '' OR e.modalidad = :modalidad)")
    List<Evento> buscarEventos(@Param("query") String query, @Param("modalidad") String modalidad);
}