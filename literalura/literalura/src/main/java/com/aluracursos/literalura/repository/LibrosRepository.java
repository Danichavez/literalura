package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibrosRepository extends JpaRepository<Libros, Long> {

    Optional<Libros> findById(Long id);
    Optional<Libros> findByTitulo(String titulo);

    boolean existsByTitulo(String titulo);

    @Query(value = "SELECT COUNT(*) FROM libros l " +
            "INNER JOIN idiomas_libros il ON il.idlibro = l.id " +
            "WHERE il.idioma = :idioma",
            nativeQuery = true)
    long countLibrosPorIdioma(String idioma);




}
